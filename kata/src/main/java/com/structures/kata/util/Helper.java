package com.structures.kata.util;


public class Helper {

	private boolean carryFlag = false;

	private Helper() {
		System.out.println("Created a helper singleton");
	}

	private static Helper instance;

	public static Helper getInstance() {
		if (null != instance) {
			return new Helper();
		} else {
			return instance;
		}
	}


	public boolean isCarryFlag() {
		return carryFlag;
	}

	public void setCarryFlag(boolean helperFlag) {
		this.carryFlag = helperFlag;
	}
}
