package com.dimasari.olshop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.request.CreateProductRequest;
import com.dimasari.olshop.dto.request.UpdateProductRequest;
import com.dimasari.olshop.dto.response.CreateProductResponse;
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

			productRepository.saveAndFlush(product);

			response = new UpdateProductResponse();
			response.setSuccess(true);
			response.setId(product.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ResponseUtil.constructBaseResponse(response);
	}
}
