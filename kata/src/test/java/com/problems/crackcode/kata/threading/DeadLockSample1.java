package com.problems.crackcode.kata.threading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSample1 {
	public Lock lock1 = new ReentrantLock();
	public Lock lock2 = new ReentrantLock();

	@Test
	@DisplayName("Test DeadLock")
	void testDeadLock() throws InterruptedException {
		DeadLockSample1 s1 = new DeadLockSample1();
		DeadLkThread1 dlk1 = new DeadLkThread1(s1);
		DeadLkThread2 dlk2 = new DeadLkThread2(s1);

		Thread t1 = new Thread(dlk1);
		Thread t2 = new Thread(dlk2);

		t1.start();
		t2.start();

		while (t1.getState() != Thread.State.TERMINATED && t2.getState() != Thread.State.TERMINATED) {
			System.out.println("Thread 1 is in state : " + t1.getState());
			System.out.println("Thread 2 is in state : " + t2.getState());
			System.out.println();
		}
		t1.join();
		t2.join();

		System.out.println("Invoked Joins on t1 & t2");

		Assertions.assertEquals(Thread.State.TERMINATED, t1.getState(), "Thread 1 should have been terminated. Deadlock !");
		Assertions.assertEquals(Thread.State.TERMINATED, t2.getState(), "Thread 1 should have been terminated. Deadlock !");
	}


}


class DeadLkThread1 implements Runnable {
	DeadLockSample1 s1;

	public DeadLkThread1(DeadLockSample1 s1) {
		this.s1 = s1;
	}

	@Override
	public void run() {
		System.out.println("Running Deadlock Thread 1");
		s1.lock1.lock();
		System.out.println("Acquired Lock 1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		s1.lock2.lock();
		System.out.println("Acquired Lock 2");
		s1.lock1.unlock();
		s1.lock2.unlock();
	}
}


class DeadLkThread2 implements Runnable {
	DeadLockSample1 s1;

	public DeadLkThread2(DeadLockSample1 s1) {
		this.s1 = s1;
	}

	@Override
	public void run() {
		System.out.println("Running Deadlock Thread 2");
		s1.lock2.lock();
		System.out.println("Acquired Lock 2");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}


		s1.lock1.lock();
		System.out.println("Acquired Lock 1");
		s1.lock2.unlock();
		s1.lock1.unlock();
	}
}