package com.dimasari.olshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.request.CreateProductRequest;
import com.dimasari.olshop.dto.request.UpdateProductRequest;
import com.dimasari.olshop.dto.response.CreateProductResponse;
import com.dimasari.olshop.dto.response.UpdateProductResponse;
import com.dimasari.olshop.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public BaseResponse<CreateProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
		return productService.create(request);
	}
	
	@PostMapping("/update")
	public BaseResponse<UpdateProductResponse> update(@Valid @RequestBody UpdateProductRequest request) {
		return productService.update(request);
	}
}
