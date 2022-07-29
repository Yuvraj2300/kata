package com.structures.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {

	Queue queue;

	@BeforeEach
	private void init() {
		queue = new Queue();
	}

	@Test
	void test() {
		Object peek = queue.peek();
		assertNull(peek);

		queue.push(1);
		Object pop = queue.pop();
		assertEquals(1, pop);
	}


	@Test
	void test_2() {
		queue.push(1);
		Object peek = queue.peek();
		assertEquals(1, peek);
	}


	@Test
	void test_3() {
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);

		int size = queue.size();

		assertEquals(4, size);

		Object peek = queue.peek();
		assertEquals(1, peek);

		queue.pop();

		Object peek2 = queue.peek();

		assertEquals(2, peek2);

		int size1 = queue.size();

		assertEquals(3, size1);

	}
}
