package com.dimasari.olshop.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dimasari.olshop.dto.AddToCartDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddToCartRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948889316523672860L;
	
	@NotNull
	@Min(value = 1)
	private Long userId;
	
	@NotNull
	@NotEmpty
	private List<AddToCartDto> carts;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public List<AddToCartDto> getCarts() {
		return carts;
	}
	
	public void setCarts(List<AddToCartDto> carts) {
		this.carts = carts;
	}
}
