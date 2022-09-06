package com.dimasari.olshop.enumeration;

public enum StatusTransaction {
	WAITING("WAITING"),
	PENDING("PENDING"),
	SETTLE("SETTLE"),
	CANCEL("CANCEL");

	private String value;
	
	StatusTransaction(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
