package com.dimasari.olshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.request.CreateProductRequest;
import com.dimasari.olshop.dto.response.CreateProductResponse;
import com.dimasari.olshop.model.Product;
import com.dimasari.olshop.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public BaseResponse<CreateProductResponse> create(CreateProductRequest request) {
		try {
			var product = new Product();
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			product.setImage(request.getImage());
			product.setPrice(request.getPrice());
			
			productRepository.saveAndFlush(product);
			
			var createProductResp = new CreateProductResponse();
			createProductResp.setSuccess(true);
			createProductResp.setId(product.getId());
			
			var response = new BaseResponse<>(createProductResp);
			response.success();
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
