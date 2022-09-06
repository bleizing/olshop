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
	
	@Column
	private Long userId;
}
