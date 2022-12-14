package com.dimasari.olshop.service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.CartDto;
import com.dimasari.olshop.dto.request.AddToCartRequest;
import com.dimasari.olshop.dto.request.PaymentRequest;
import com.dimasari.olshop.dto.request.RemoveFromCartRequest;
import com.dimasari.olshop.dto.response.AddToCartResponse;
import com.dimasari.olshop.dto.response.CheckoutResponse;
import com.dimasari.olshop.dto.response.GetCartResponse;
import com.dimasari.olshop.dto.response.PaymentResponse;
import com.dimasari.olshop.dto.response.RemoveFromCartResponse;
import com.dimasari.olshop.enumeration.StatusCart;
import com.dimasari.olshop.enumeration.StatusTransaction;
import com.dimasari.olshop.exception.AmountLessPriceException;
import com.dimasari.olshop.exception.DataNotFoundException;
import com.dimasari.olshop.exception.UserUnregisterException;
import com.dimasari.olshop.model.Cart;
import com.dimasari.olshop.model.CartUserDetail;
import com.dimasari.olshop.model.Transaction;
import com.dimasari.olshop.model.TransactionDetail;
import com.dimasari.olshop.repo.CartRepository;
import com.dimasari.olshop.repo.CartUserDetailRepository;
import com.dimasari.olshop.repo.ProductRepository;
import com.dimasari.olshop.repo.TransactionDetailRepository;
import com.dimasari.olshop.repo.TransactionRepository;
import com.dimasari.olshop.repo.UserRepository;
import com.dimasari.olshop.util.ResponseUtil;

@Service
public class TransactionService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartUserDetailRepository cartUserDetailRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionDetailRepository transactionDetailRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public BaseResponse<AddToCartResponse> addToCart(AddToCartRequest request) {
		AddToCartResponse response = null;
		try {
			var userId = request.getUserId();
			
			validateUser(userId);
			
			Cart cart;
			
			var cartOptional = cartRepository.findByUserIdAndStatusInCart(userId);
			if (cartOptional.isPresent()) {
				cart = cartOptional.get();
				cart.setModifiedAt(LocalDateTime.now());
			} else {
				cart = new Cart();
				cart.setCreatedAt(LocalDateTime.now());
				cart.setStatus(StatusCart.CART.getValue());
				cart.setUserId(userId);
			}

			cartRepository.saveAndFlush(cart);
			
			request.getCarts().forEach(data -> {
				productRepository.findByIdAndDeletedIsFalse(data.getProductId()).orElseThrow(() -> new DataNotFoundException("Product not found"));
				var cartUserDetailOptional = cartUserDetailRepository.findByCartIdAndProductIdAndDeletedFalse(cart.getId(), data.getProductId());
				CartUserDetail cartUserDetail = null;
				if (cartUserDetailOptional.isPresent()) {
					cartUserDetail = cartUserDetailOptional.get();
					cartUserDetail.setModifiedAt(LocalDateTime.now());
					if (data.getQuantity() > 0) {
						cartUserDetail.setQuantity(data.getQuantity());
					} else {
						cartUserDetail.setDeleted(true);
					}
				} else {
					if (data.getQuantity() > 0) {
						cartUserDetail = new CartUserDetail();
						cartUserDetail.setCreatedAt(LocalDateTime.now());
						cartUserDetail.setCartId(cart.getId());
						cartUserDetail.setProductId(data.getProductId());
						cartUserDetail.setQuantity(data.getQuantity());
					}
				}
				if (cartUserDetail != null) {
					cartUserDetailRepository.save(cartUserDetail);
				}
			});
			
			var cartDetailOptional = cartUserDetailRepository.findByCartIdAndDeletedFalse(cart.getId());
			if (!cartDetailOptional.isPresent()) {
				cart.setDeleted(true);
			}
			
			cartRepository.save(cart);
			
			response = new AddToCartResponse();
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<RemoveFromCartResponse> removeFromCart(RemoveFromCartRequest request) {
		RemoveFromCartResponse response = null;
		try {
			validateUser(request.getUserId());
			
			var deletedData = new ArrayList<Long>();
			
			var cart = cartRepository.findByUserIdAndStatusInCart(request.getUserId()).orElseThrow(() -> new DataNotFoundException("Cart not found"));
			var cartUserDetail = cartUserDetailRepository.findByCartIdAndDeletedFalse(cart.getId()).orElseThrow(() -> new DataNotFoundException("Cart detail not found"));
			request.getIdProducts().forEach(data -> {
				productRepository.findByIdAndDeletedIsFalse(data).orElseThrow(() -> new DataNotFoundException("Product not found"));
				if (cartUserDetail.stream().anyMatch(cartUserDetailData -> cartUserDetailData.getProductId() == data)) {
					var cartUserDetailData = cartUserDetailRepository.findByCartIdAndProductIdAndDeletedFalse(cart.getId(), data).orElseThrow(() -> new DataNotFoundException("Cart detail not found"));
					cartUserDetailData.setModifiedAt(LocalDateTime.now());
					cartUserDetailData.setDeleted(true);
					cartUserDetailRepository.save(cartUserDetailData);
					deletedData.add(data);
				}
			});
			
			if (deletedData.size() == cartUserDetail.size()) {
				cart.setDeleted(true);
			} else {
				cart.setModifiedAt(LocalDateTime.now());
			}

			cartRepository.save(cart);
			
			response = new RemoveFromCartResponse();
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<GetCartResponse> getCart(Long userId) {
		GetCartResponse response = null;
		try {
			validateUser(userId);
			
			ArrayList<CartDto> cartDtoList = new ArrayList<>();
			var cartOptional = cartRepository.findByUserIdAndStatusInCart(userId);
			if (cartOptional.isPresent()) {
				var cart = cartOptional.get();
				var cartDetailOptional = cartUserDetailRepository.findByCartIdAndDeletedFalse(cart.getId());
				if (cartDetailOptional.isPresent()) {
					var cartDetails = cartDetailOptional.get();
					cartDetails.forEach(data -> {
						var product = productRepository.findByIdAndDeletedIsFalse(data.getProductId()).orElseThrow(() -> new DataNotFoundException("Product not found"));
						cartDtoList.add(new CartDto(product.getId(), product.getName(), product.getImage(), product.getPrice(), data.getQuantity()));
					});
				}
			}
			
			response = new GetCartResponse();
			response.setCarts(cartDtoList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<CheckoutResponse> checkout(Long userId) {
		CheckoutResponse response = null;
		try {
			validateUser(userId);
			
			var cart = cartRepository.findByUserIdAndStatusInCart(userId).orElseThrow(() -> new DataNotFoundException("Cart not found"));
			
			int[] totalQuantity = {0};
			var vaNumber = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			int[] totalPrice = {0};
			var transactionRef = "OL" + vaNumber + randomAlphabet(3);
			var cartDetails = cartUserDetailRepository.findByCartIdAndDeletedFalse(cart.getId()).orElseThrow(() -> new DataNotFoundException("Cart detail not found"));
			
			var transaction = new Transaction();
			transaction.setCreatedAt(LocalDateTime.now());
			transaction.setUserId(userId);
			transaction.setStatus(StatusTransaction.WAITING.getValue());
			transaction.setOrderDate(LocalDateTime.now());
			transactionRepository.saveAndFlush(transaction);
			
			cartDetails.forEach(data -> {
				var product = productRepository.findByIdAndDeletedIsFalse(data.getProductId()).orElseThrow(() -> new DataNotFoundException("Product not found"));
				totalQuantity[0] += data.getQuantity();
				var total = product.getPrice().multiply(BigDecimal.valueOf(data.getQuantity()));
				totalPrice[0] += Integer.valueOf(total.toPlainString());
				
				var transactionDetail = new TransactionDetail();
				transactionDetail.setCreatedAt(LocalDateTime.now());
				transactionDetail.setTransactionRef(transactionRef);
				transactionDetail.setProductName(product.getName());
				transactionDetail.setProductDescription(product.getDescription());
				transactionDetail.setTotalQuantity(data.getQuantity());
				transactionDetail.setTotalPrice(total);
				
				transactionDetailRepository.save(transactionDetail);
			});
			transaction.setTotalQuantity(totalQuantity[0]);
			transaction.setTotalPrice(BigDecimal.valueOf(totalPrice[0]));
			transaction.setVaNumber(vaNumber);
			transaction.setTransactionRef(transactionRef);
			transactionRepository.save(transaction);
			
			cart.setStatus(StatusCart.TRANSACTION.getValue());
			cartRepository.save(cart);
			
			response = new CheckoutResponse();
			response.setTotalPrice(BigDecimal.valueOf(totalPrice[0]));
			response.setTransactionRef(transactionRef);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<PaymentResponse> payment(PaymentRequest request) {
		PaymentResponse response = null;
		try {
			var userId = request.getUserId();
			
			validateUser(userId);
			
			var transaction = transactionRepository.findByUserIdAndTransactionRefAndStatusInWaiting(userId, request.getTransactionRef()).orElseThrow(() -> new DataNotFoundException("Transaction not found"));
			var transactionDetails = transactionDetailRepository.findByTransactionRefAndDeletedIsFalse(transaction.getTransactionRef()).orElseThrow(() -> new DataNotFoundException("Transaction detail not found"));
			
			int[] totalPrice = {0};
			
			transactionDetails.forEach(data -> {
				totalPrice[0] += Integer.valueOf(data.getTotalPrice().toPlainString());
			});
			
			if (request.getAmount().compareTo(BigDecimal.valueOf(totalPrice[0])) < 0) {
				throw new AmountLessPriceException("Amount is less than price");
			}
			
			transaction.setModifiedAt(LocalDateTime.now());
			transaction.setPaymentDate(LocalDateTime.now());
			transaction.setPaymnetMethod(request.getPaymentMethod());
			transaction.setPaymentAmount(request.getAmount());
			transaction.setStatus(StatusTransaction.SETTLE.getValue());
			transactionRepository.save(transaction);
			
			response = new PaymentResponse();
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return ResponseUtil.constructBaseResponse(response);
	}
	
	private void validateUser(Long userId) {
		userRepository.findByIdAndDeletedIsFalse(userId).orElseThrow(() -> new UserUnregisterException("User unregister"));
	}
	
	private String randomAlphabet(int len) {
		var rnd = new SecureRandom();
		var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
		return sb.toString();
	}
}
