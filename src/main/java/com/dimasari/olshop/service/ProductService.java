package com.dimasari.olshop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.request.CreateProductRequest;
import com.dimasari.olshop.dto.request.DeleteProductRequest;
import com.dimasari.olshop.dto.request.UpdateProductRequest;
import com.dimasari.olshop.dto.response.CreateProductResponse;
import com.dimasari.olshop.dto.response.DeleteProductResponse;
import com.dimasari.olshop.dto.response.DetailProductResponse;
import com.dimasari.olshop.dto.response.UpdateProductResponse;
import com.dimasari.olshop.exception.DataNotFoundException;
import com.dimasari.olshop.model.Product;
import com.dimasari.olshop.repo.ProductRepository;
import com.dimasari.olshop.util.ResponseUtil;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public BaseResponse<CreateProductResponse> create(CreateProductRequest request) {
		CreateProductResponse response = null;
		try {
			var product = new Product();
			product.setCreatedAt(LocalDateTime.now());
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			product.setImage(request.getImage());
			product.setPrice(request.getPrice());

			productRepository.saveAndFlush(product);

			response = new CreateProductResponse();
			response.setSuccess(true);
			response.setId(product.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<UpdateProductResponse> update(UpdateProductRequest request) {
		UpdateProductResponse response = null;
		try {
			var product = productRepository.findByIdAndDeletedIsFalse(request.getProductId()).orElseThrow(() -> new DataNotFoundException("Product not found"));
			product.setModifiedAt(LocalDateTime.now());
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			product.setImage(request.getImage());
			product.setPrice(request.getPrice());

			productRepository.save(product);

			response = new UpdateProductResponse();
			response.setSuccess(true);
			response.setId(product.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<DeleteProductResponse> delete(DeleteProductRequest request) {
		DeleteProductResponse response = null;
		try {
			var product = productRepository.findByIdAndDeletedIsFalse(request.getProductId()).orElseThrow(() -> new DataNotFoundException("Product not found"));
			product.setModifiedAt(LocalDateTime.now());
			product.setDeleted(true);

			productRepository.save(product);

			response = new DeleteProductResponse();
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ResponseUtil.constructBaseResponse(response);
	}
	
	public BaseResponse<DetailProductResponse> detail(Long id) {
		DetailProductResponse response = null;
		try {
			var product = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new DataNotFoundException("Product not found"));

			response = new DetailProductResponse();
			response.setName(product.getName());
			response.setDescription(product.getDescription());
			response.setImage(product.getImage());
			response.setPrice(product.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ResponseUtil.constructBaseResponse(response);
	}
}
