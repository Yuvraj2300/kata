package com.problems.crackcode.kata.exceptions;

public class KataException extends RuntimeException {

	private String message;

	public KataException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
