package com.problems.crackcode.kata.threading.cancellation;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable {
	private volatile boolean cancelFlag = false;
	private final List<Integer> list;

	public PrimeGenerator(List<Integer> list) {
		this.list = list;
	}

	private List<Integer> getPrimes() {
		BigInteger bi = BigInteger.ONE;
		while (!cancelFlag) {
			synchronized (this) {
				list.add(bi.nextProbablePrime().intValue());
			}
		}
		return list;
	}


	public void cancel() {
		synchronized (this) {
			this.cancelFlag = true;
		}
	}



	@Override
	public void run() {
		getPrimes();
	}
}


class TestPrimeGenerator {
	public static void main(String[] args) {
		LinkedList<Integer> generatedPrimes = new LinkedList<>();
		PrimeGenerator primeGenerator = new PrimeGenerator(generatedPrimes);
		Thread t = new Thread(primeGenerator);

		t.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			primeGenerator.cancel();
		}


		System.out.println(generatedPrimes.size());
	}
}