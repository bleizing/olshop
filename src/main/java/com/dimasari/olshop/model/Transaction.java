package com.dimasari.olshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dimasari.olshop.enumeration.PaymnetMethod;
import com.dimasari.olshop.enumeration.StatusTransaction;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6204095731084633921L;

	@Column(name = "user_id")
	private Long userId;
	
	@Column
	private String status;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "order_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime orderDate;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "payment_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime paymentDate;
	
	@Column(name = "total_quantity")
	private int totalQuantity;
	
	@Column(name = "total_price", precision = 19, scale = 0)
	private BigDecimal totalPrice;
	
	@Column(name = "payment_method")
	private PaymnetMethod paymnetMethod;
	
	@Column(name = "va_number")
	private String vaNumber;
	
	@Column(name = "payment_amount", precision = 19, scale = 0)
	private BigDecimal paymentAmount;
	
	@Column(name = "transaction_ref")
	private String transactionRef;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
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

	public PaymnetMethod getPaymnetMethod() {
		return paymnetMethod;
	}

	public void setPaymnetMethod(PaymnetMethod paymnetMethod) {
		this.paymnetMethod = paymnetMethod;
	}

	public String getVaNumber() {
		return vaNumber;
	}

	public void setVaNumber(String vaNumber) {
		this.vaNumber = vaNumber;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
}
