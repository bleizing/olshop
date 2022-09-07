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
import com.dimasari.olshop.dto.request.CreateProductRequest;
import com.dimasari.olshop.dto.request.DeleteProductRequest;
import com.dimasari.olshop.dto.request.UpdateProductRequest;
import com.dimasari.olshop.dto.response.CreateProductResponse;
import com.dimasari.olshop.dto.response.DeleteProductResponse;
import com.dimasari.olshop.dto.response.DetailProductResponse;
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
	
	@PostMapping("/delete")
	public BaseResponse<DeleteProductResponse> delete(@Valid @RequestBody DeleteProductRequest request) {
		return productService.delete(request);
	}
	
	@GetMapping("detail/{id}")
	public BaseResponse<DetailProductResponse> detail(@PathVariable("id") Long id) {
		return productService.detail(id);
	}
}
