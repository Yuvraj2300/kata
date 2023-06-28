package com.problems.crackcode.kata.threading.synchronizers.cdl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class MyTimerTest {

	@Test
	@DisplayName("Test Timer")
	void testTimer() {
		MyTimer timer = new MyTimer(5, () -> _generateWork(new Random().nextInt()));
		try {
			timer.timeMyTasks();
		} catch (InterruptedException ex) {
			System.out.println("Error : " + ex);
		}
	}

	private void _generateWork(int value) {
		int calc = (value + 1) * value;
		// Loop for a large number of times to simulate a long running calculation
		for (int i = 0; i < 1000000; i++) {
			// Perform some random arithmetic operations
			calc += i * i;
			calc -= i / 2;
			calc *= i + 1;
			calc /= i + 2;
		}
		System.out.println("LOG : " + Thread.currentThread().getName() + " - Calculated : " + calc + " For expression : " + "( " + value + " + " + "1) * " + value);
	}
}
