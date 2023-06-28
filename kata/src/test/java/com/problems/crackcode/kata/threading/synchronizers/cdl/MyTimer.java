package com.problems.crackcode.kata.threading.synchronizers.cdl;

import java.util.concurrent.CountDownLatch;

public class MyTimer {
	private int nThreads;
	private Runnable task;

	public MyTimer(int nThreads, Runnable task) {
		this.nThreads = nThreads;
		this.task = task;
	}


	public Long timeMyTasks() throws InterruptedException {
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch endLatch = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						startLatch.await();
						try {
							task.run();
						} finally {
							endLatch.countDown();
						}
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}, "worker-" + i);
			t.start();
		}

		long startTime = System.nanoTime();
		startLatch.countDown();
		endLatch.await();
		long endTime = System.nanoTime();

		return endTime - startTime;
	}
}
