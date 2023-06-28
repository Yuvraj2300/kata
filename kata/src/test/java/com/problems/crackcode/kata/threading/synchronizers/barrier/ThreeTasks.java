package com.problems.crackcode.kata.threading.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreeTasks {


	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, () -> {
			System.out.println("Running Extra Task by thread : " + Thread.currentThread().getName());
		});

		Thread t1 = new Thread(new MyTask(barrier));
		Thread t2 = new Thread(new MyTask(barrier));
		Thread t3 = new Thread(new MyTask(barrier));

		t1.start();
		t2.start();
		t3.start();
	}


}

class MyTask implements Runnable {
	CyclicBarrier barrier;

	public MyTask(CyclicBarrier barrier) {
		this.barrier = barrier;
	}


	@Override
	public void run() {
		try {
			System.out.println("Thread " + Thread.currentThread().getName() + " is reaching barrier");
			barrier.await();
			System.out.println("Thread " + Thread.currentThread().getName() + " has crossed the");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
