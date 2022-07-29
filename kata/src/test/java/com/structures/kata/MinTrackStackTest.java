package com.structures.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinTrackStackTest {

	MinTrackStack stack;

	@BeforeEach
	void init() {
		stack = new MinTrackStack();
	}

	@Test
	void test() {
		stack.push(2);

		assertEquals(2, stack.min());

		stack.push(1);

		stack.push(2);

		assertEquals(1, stack.min());

		stack.push(3);

		assertEquals(1, stack.min());

		stack.push(5);

		assertEquals(1, stack.min());

		stack.pop();
		stack.pop();
		stack.pop();

		assertEquals(1, stack.min());

		stack.pop();

		assertEquals(2, stack.min());
	}

}
