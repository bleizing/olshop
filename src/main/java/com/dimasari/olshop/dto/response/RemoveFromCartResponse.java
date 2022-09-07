package com.dimasari.olshop.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RemoveFromCartResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9130059044429608378L;
	
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
