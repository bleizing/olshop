package com.dimasari.olshop.exception;

public class AmountLessPriceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727614147371229961L;

	public AmountLessPriceException(String message) {
		super(message);
	}
}
