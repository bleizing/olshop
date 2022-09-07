package com.dimasari.olshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dimasari.olshop.enumeration.StatusCart;

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
	private StatusCart status;
}
