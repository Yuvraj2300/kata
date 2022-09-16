package com.problems.crackcode.kata.apis.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

public class ConcurrencySandbox {

	public long calcExecTime(Executor executor, int concurrency, Runnable task) {
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(concurrency);

		for (int i = 0; i < concurrency; i++) {
			ready.countDown();
			executor.execute(() -> {
				try {
					start.await();
					task.run();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					done.countDown();
				}
			});
		}

		long timeTaken = 0;

		try {
			ready.await();

			long startTime = System.nanoTime();

			start.countDown();
			done.await();

			timeTaken = System.nanoTime() - startTime;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return timeTaken;
	}



	private int calculateFactorial(int n) {
		if (n == 0)
			return 1;

		return n * calculateFactorial(n - 1);
	}




	@Test
	void testTimeTaken3Threads() throws Exception {
		ExecutorService tpe = Executors.newSingleThreadExecutor();

		Runnable task = () -> {
			Random random = new Random();
			System.out.println(calculateFactorial(random.nextInt(10)));
		};

		long timeTaken = calcExecTime(tpe, 3, task);
		System.out.println("Time taken to execute the tasks : " + timeTaken);
	}
}
