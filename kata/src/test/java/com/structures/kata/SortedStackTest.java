package com.structures.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedStackTest {
	SortedStack sortedStack;

	@BeforeEach
	void init() {
		sortedStack = new SortedStack();
	}

	@Test
	void test() {
		sortedStack.push(4);
		sortedStack.push(5);
		sortedStack.push(8);
		sortedStack.push(2);
		sortedStack.push(16);

		System.out.println(sortedStack);

		assertEquals(2, sortedStack.pop());
		assertEquals(4, sortedStack.pop());

	}

}
