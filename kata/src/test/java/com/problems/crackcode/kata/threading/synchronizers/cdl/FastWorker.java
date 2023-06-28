package com.problems.crackcode.kata.threading.synchronizers.cdl;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class FastWorker implements Runnable {
	CountDownLatch starter;
	CountDownLatch finisher;

	Runnable task;

	int parallelization;

	public FastWorker(Runnable task, int parallelization) {
		this.task = task;
		this.parallelization = parallelization;
		starter = new CountDownLatch(1);
		finisher = new CountDownLatch(parallelization);
	}


	@Override
	public void run() {
		List<Thread> threads = new ArrayList<>();
		try {
			for (int i = 0; i < parallelization; i++) {
				Thread t = new Thread(() -> {
					try {
						starter.await();
						try {
							task.run();
						} finally {
							finisher.countDown();
						}
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}, "worker-" + i);
				t.start();
				threads.add(t);
			}
			starter.countDown();
			finisher.await();
		} catch (InterruptedException e) {
			System.out.println("There was a problem with - " + Thread.currentThread().getName());
			for (Thread t : threads) {
				t.interrupt();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		FastWorker fw = new FastWorker(() -> _someWork(), 4);
		Thread t = new Thread(fw);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				t.interrupt();
				timer.cancel();
			}

		}, 4000);

		t.start();
		t.join();

		System.out.println("STATE : " + t.getState());
		System.out.println("Done ");
	}

	private static void _someWork() {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("The Thread was interrupted : " + Thread.currentThread().getName());
				break;
			}
			int compute = (int) (i * rand.nextInt() * Math.pow(i, rand.nextInt()));
			System.out.print("Compute Was : " + compute + " by :  " + Thread.currentThread().getName());
			System.out.println();
		}
	}

}
