package com.sk.product.domain.usecase;

public class NotExistProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotExistProductException() {
		super();
	}

	public NotExistProductException(String message) {
		super(message);
	}
}