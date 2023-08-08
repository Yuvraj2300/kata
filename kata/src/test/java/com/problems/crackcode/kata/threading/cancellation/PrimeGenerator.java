package com.problems.crackcode.kata.threading.cancellation;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable {
	private volatile boolean cancelFlag = false;

	BlockingQueue<Integer> primesQueue;

	public PrimeGenerator(BlockingQueue<Integer> primesQueue) {
		this.primesQueue = primesQueue;
	}

	void primeGenerator() {
		BigInteger bi = BigInteger.ONE;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				bi = bi.nextProbablePrime();
				primesQueue.put(bi.intValue());
			} catch (InterruptedException e) {
				System.out.println("Prime Generator Thread was interrupted, thread name : " + Thread.currentThread().getName());
				//preserving the interrupted status
				Thread.currentThread().interrupt();
			}
		}
	}


	public void cancel() {
		Thread.currentThread().interrupt();
	}



	@Override
	public void run() {
		primeGenerator();
	}
}


class PrimeConsumer implements Runnable {
	BlockingQueue<Integer> primesQueue;

	public PrimeConsumer(BlockingQueue<Integer> primesQueue) {
		this.primesQueue = primesQueue;
	}

	private void consumePrimes() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Integer currPrime = primesQueue.take();
				System.out.print("Prime Observed : " + currPrime + ", ");
			} catch (InterruptedException e) {
				System.out.println("Prime Consumer Thread was interrupted, thread name : " + Thread.currentThread().getName());
				//preserving the interrupted status
				Thread.currentThread().interrupt();
			}
		}
	}

	public void cancel() {
		Thread.currentThread().interrupt();
	}

	@Override
	public void run() {
		consumePrimes();
	}
}

class TestPrimeGenerator {
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> primesQueue = new ArrayBlockingQueue<>(10);

		Thread generatorThread = new Thread(new PrimeGenerator(primesQueue));
		Thread consumerThread = new Thread(new PrimeConsumer(primesQueue));
		generatorThread.start();
		consumerThread.start();


		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			generatorThread.interrupt();
			consumerThread.interrupt();
			System.out.println();
			System.out.println("State of the shared queue : ");
			System.out.println(primesQueue);
		}

	}
}