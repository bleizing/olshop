package com.dimasari.olshop.dto.response;

import java.io.Serializable;

public class CreateProductResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5435461192595675901L;
	
	private boolean success;
	private Long id;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
