package com.dimasari.olshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart_user_details")
public class CartUserDetail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3281912592838892165L;

	@Column(name = "cart_id")
	private Long cartId;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column
	private int quantity;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
