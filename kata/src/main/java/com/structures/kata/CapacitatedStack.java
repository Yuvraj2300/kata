package com.structures.kata;

import java.lang.reflect.Array;

public class CapacitatedStack<E> implements Stack {
	private static final int INITIAL_CAPACITY = 16;
	private E[] elements;
	private int size = 0;;



	public CapacitatedStack(Class<E> clazz) {
		elements = (E[]) Array.newInstance(clazz, INITIAL_CAPACITY);
	}



	public void push(E data) throws Exception {
		if (size == elements.length) {
			throw new Exception("stack is full");
		}

		elements[size++] = data;
	}



	public E pop() throws Exception {
		if (size == 0) {
			System.out.println("stack was empty");
		}

		E toReturn = elements[--size];
		elements[size] = null;

		return toReturn;
	}



	public E peek() {
		return elements[size - 1];
	}



	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}

		return false;
	}
}
