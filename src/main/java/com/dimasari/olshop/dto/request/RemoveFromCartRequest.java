package com.dimasari.olshop.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RemoveFromCartRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5782834180785144879L;
	
	@NotNull
	@Min(value = 1)
	private Long userId;
	
	@NotNull
	@NotEmpty
	private List<Long> idProducts;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getIdProducts() {
		return idProducts;
	}

	public void setCarts(List<Long> idProducts) {
		this.idProducts = idProducts;
	}
}
