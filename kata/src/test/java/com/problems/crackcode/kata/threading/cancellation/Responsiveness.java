package com.problems.crackcode.kata.threading.cancellation;

import java.util.concurrent.CyclicBarrier;

public class Responsiveness {

	public static void main(String[] args) {
		//LowLy Responsive Thread;
		Thread t1 = new Thread(new HighlyResponsiveTask());
		Thread t2 = new Thread(new LowlyResponsiveTask());

		t1.start();
		t2.start();


		t1.interrupt();
		t2.interrupt();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}


	}
}

class HighlyResponsiveTask implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("From Highly Responsive " + i);

			for (int j = 0; j < 10; j++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("Highly was interrupted");
					Thread.currentThread().interrupt();
					//do nothing
				}
				if (Thread.interrupted()) {
					//preserve the status
					System.out.println("Highly was interrupted");
					Thread.currentThread().interrupt();
					return;
				}
			}
		}
	}
}

class LowlyResponsiveTask implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("From Lowly Responsive " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Lowly was interrupted");
				Thread.currentThread().interrupt();
				//do nothing
			}

			if (Thread.interrupted()) {
				System.out.println("Lowly was interrupted");
				//preserve the status
				Thread.currentThread().interrupt();
				return;
			}
		}
	}
}