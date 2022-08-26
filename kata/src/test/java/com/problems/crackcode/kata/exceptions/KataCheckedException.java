package com.problems.crackcode.kata.exceptions;

public class KataCheckedException extends Exception {

	private String message;

	public KataCheckedException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
