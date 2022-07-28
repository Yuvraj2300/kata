package com.structures.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueWithStacksTest {

	QueueWithStacks queue;

	@BeforeEach
	void init() {
		queue = new QueueWithStacks();
	}


	@Test
	void test() {
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);


		assertEquals(1, queue.peek());
		queue.pop();
		assertEquals(2, queue.peek());
	}

}
