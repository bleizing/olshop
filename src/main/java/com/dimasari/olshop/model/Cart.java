package com.dimasari.olshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart_users")
public class Cart extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7995792177578239312L;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column
	private String status;

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
}
