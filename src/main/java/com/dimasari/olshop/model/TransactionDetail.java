package com.dimasari.olshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetail extends BaseModel {
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
}
