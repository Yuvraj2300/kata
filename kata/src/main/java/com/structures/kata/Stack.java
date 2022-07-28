package com.structures.kata;

import java.util.Arrays;

public class Stack {
	private static final int INITIAL_CAPACITY = 16;
	private Object[] elements;
	private int size = 0;;

	public Stack() {
		elements = new Object[INITIAL_CAPACITY];
	}


	public void push(Object data) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}

		elements[size++] = data;
	}

	public Object pop() throws Exception {
		if (size == 0) {
			throw new Exception("stack was already empty");
		}

		Object toReturn = elements[--size];
		elements[size] = null;

		return toReturn;
	}


	public Object peek() {
		return elements[size];
	}


}
