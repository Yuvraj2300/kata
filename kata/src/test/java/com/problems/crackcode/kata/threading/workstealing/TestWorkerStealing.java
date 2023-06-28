package com.problems.crackcode.kata.threading.workstealing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TestWorkerStealing {

	@Test
	@DisplayName("Test Work Stealing")
	void testWorkStealing() throws InterruptedException {
		Deque<Runnable> deque = new ConcurrentLinkedDeque<>();

		List<Worker> listOfWorkers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			listOfWorkers.add(new Worker(deque, listOfWorkers));
		}

		for (int i = 0; i < 100; i++) {
			int taskNumber = i;
			deque.offer(() -> {
				_generateWork(taskNumber);
			});
		}

		List<Thread> listOfThreads = new ArrayList<>();

		for (int i = 0; i < listOfWorkers.size(); i++) {
			Thread t = new Thread(listOfWorkers.get(i));
			t.setName("workStealer-" + i);
			t.start();
			listOfThreads.add(t);
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (Thread t : listOfThreads) {
					t.interrupt();
				}
			}
		}, 20000);


		for (Thread t : listOfThreads) {
			t.join();
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
