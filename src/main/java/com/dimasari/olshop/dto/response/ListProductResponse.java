package com.dimasari.olshop.dto.response;

import java.io.Serializable;
import java.util.List;

import com.dimasari.olshop.dto.BaseResponsePagination;
import com.dimasari.olshop.dto.ProductDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ListProductResponse extends BaseResponsePagination implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5536383288196979733L;
	
	public List<ProductDto> products;

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
