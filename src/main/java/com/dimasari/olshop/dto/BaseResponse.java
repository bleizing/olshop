package com.dimasari.olshop.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5323453477826563985L;
	
	private String responseCode;
	private String responseMessage;
	private T data;
	
	public BaseResponse(T data) {
		this.data = data;
	}

	public String getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public void success() {
		this.responseCode = "00";
		this.responseMessage = "success";
	}
}
