package com.dimasari.olshop.enumeration;

public enum PaymnetMethod {
	BCA("BCA"),
	BNI("BNI"),
	MANDIRI("MANDIRI"),
	PERMATA("PERMATA");
	
	private String value;

	private PaymnetMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
