package com.dimasari.olshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3808545765287110593L;

	@Column(name = "transaction_ref")
	private String transactionRef;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_description", columnDefinition = "TEXT")
	private String productDescription;
	
	@Column(name = "total_quantity")
	private int totalQuantity;
	
	@Column(name = "total_price", precision = 19, scale = 0)
	private BigDecimal totalPrice;

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
