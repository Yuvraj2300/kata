package com.structures.kata;

import java.util.Arrays;

public class Queue {
	private static int INITIAL_CAPACITY = 16;
	private int size;

	Object[] elements;



	public Queue() {
		elements = new Object[INITIAL_CAPACITY];
	}



	public void push(Object data) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}


		elements[size++] = data;
	}



	public Object pop() {
		Object objectToReturn = elements[0];

		if (elements[1] != null) {
			_shiftElementsInTheArray();
			size--;
		}

		return objectToReturn;
	}



	public Object peek() {
		return elements[0];
	}



	public int size() {
		return size;
	}



	private void _shiftElementsInTheArray() {
		int arrayIt = 1;
		int i = 0;
		while (arrayIt < elements.length) {
			if (elements[arrayIt] != null) {
				elements[i] = elements[arrayIt];

			} else {
				elements[arrayIt - 1] = null;
				break;
			}
			i++;
			arrayIt++;
		}


	}

}
