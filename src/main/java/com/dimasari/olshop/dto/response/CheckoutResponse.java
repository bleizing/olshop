package com.dimasari.olshop.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckoutResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2829737055887301392L;
	
	private String transactionRef;
	private BigDecimal totalPrice;
	
	public String getTransactionRef() {
		return transactionRef;
	}
	
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
