package com.structures.kata;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InfiniteStack<E> implements Stack {
	private static final int INITIAL_CAPACITY = 16;
	private E[] elements;
	private int size = 0;;



	public InfiniteStack(Class<E> clazz) {
		elements = (E[]) Array.newInstance(clazz, INITIAL_CAPACITY);
	}



	public void push(E data) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
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
