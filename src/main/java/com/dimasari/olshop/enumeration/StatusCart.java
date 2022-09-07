package com.dimasari.olshop.enumeration;

public enum StatusCart {
	CART("CART"),
	TRANSACTION("TRANSACTION");
	
	private String value;

	private StatusCart(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
