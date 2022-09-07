package com.dimasari.olshop.dto.response;

import java.io.Serializable;
import java.util.List;

import com.dimasari.olshop.dto.CartDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetCartResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 575607026260611566L;
	
	private List<CartDto> carts;

	public List<CartDto> getCarts() {
		return carts;
	}

	public void setCarts(List<CartDto> carts) {
		this.carts = carts;
	}
}
