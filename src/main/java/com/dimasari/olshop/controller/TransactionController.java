package com.dimasari.olshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.request.AddToCartRequest;
import com.dimasari.olshop.dto.request.PaymentRequest;
import com.dimasari.olshop.dto.request.RemoveFromCartRequest;
import com.dimasari.olshop.dto.response.AddToCartResponse;
import com.dimasari.olshop.dto.response.CheckoutResponse;
import com.dimasari.olshop.dto.response.GetCartResponse;
import com.dimasari.olshop.dto.response.PaymentResponse;
import com.dimasari.olshop.dto.response.RemoveFromCartResponse;
import com.dimasari.olshop.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/cart/add")
	public BaseResponse<AddToCartResponse> addToCart(@Valid @RequestBody AddToCartRequest request) {
		return transactionService.addToCart(request);
	}
	
	@PostMapping("/cart/remove")
	public BaseResponse<RemoveFromCartResponse> removeFromCart(@Valid @RequestBody RemoveFromCartRequest request) {
		return transactionService.removeFromCart(request);
	}
	
	@GetMapping("cart/list/{userId}")
	public BaseResponse<GetCartResponse> list(@PathVariable("userId") Long userId) {
		return transactionService.getCart(userId);
	}
	
	@GetMapping("checkout/{userId}")
	public BaseResponse<CheckoutResponse> checkout(@PathVariable("userId") Long userId) {
		return transactionService.checkout(userId);
	}
	
	@PostMapping("/payment")
	public BaseResponse<PaymentResponse> payment(@Valid @RequestBody PaymentRequest request) {
		return transactionService.payment(request);
	}
}
