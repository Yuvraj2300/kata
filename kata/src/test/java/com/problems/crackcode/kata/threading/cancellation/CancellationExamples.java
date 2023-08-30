package com.problems.crackcode.kata.threading.cancellation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CancellationExamples {

}


class AbstractTaskRunnerBadCancel {

	Runnable task = new Runnable() {
		@Override
		public void run() {
			System.out.println("I am running on " + Thread.currentThread().getName());

			for (int i = 0; i < 10; i++) {
				System.out.println("Integer Val : " + i);
				i = i / 0;
			}
		}
	};


	void timedRunner(Runnable task, int timeout, TimeUnit unit) {
		task.run();
	}



}


class PrimeGeneratorError implements Runnable {
	private final List<BigInteger> primes;
	private volatile boolean cancelled;

	public PrimeGeneratorError(List<BigInteger> primes) {
		this.primes = primes;
	}

	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			// Simulate an exception (divide by zero)
			int x = 1 / 0;
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
}


class AbstractTaskRunnerGoodCancel {
	ExecutorService execSvc;

	public AbstractTaskRunnerGoodCancel(ExecutorService execSvc) {
		this.execSvc = execSvc;
	}

	public void timedRun(final Runnable task, int time, TimeUnit unit) {
		Future<?> submit = execSvc.submit(task);
		try {
			submit.get(time, unit);
		} catch (InterruptedException e) {
			System.out.println("Your task was interrupted");
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			System.out.println("Your task threw an exception buddy");
			System.out.println(e.getMessage());
			submit.cancel(true);
			throw _launderException(e);
		} catch (TimeoutException e) {
			System.out.println("Your task timed out");
		} finally {
			//setting true since assuming tasks written is responsive to interrupt
			submit.cancel(true);
			execSvc.shutdown();
		}
	}

	private static RuntimeException _launderException(ExecutionException e) {
		Throwable cause = e.getCause();
		if (cause instanceof RuntimeException) {
			throw (RuntimeException) cause;
		} else if (cause instanceof Error) {
			throw (Error) cause;
		} else {
			throw new IllegalStateException("This was not an unchecked Exception. Please check yer code");
		}
	}

}

class Runner {
	public static void main(String[] args) {
		//
		//		Thread thread = new Thread(() -> {
		//			AbstractTaskRunner atr = new AbstractTaskRunner();
		//			atr.timedRunner(atr.task, 5, TimeUnit.SECONDS);
		//		});
		//
		//		LinkedList<BigInteger> primes1 = new LinkedList<>();
		//		PrimeGeneratorError pe = new PrimeGeneratorError(primes1);
		//		Thread pT = new Thread(pe);
		//
		//		thread.start();
		//		pT.start();
		//		try {
		//			pT.join();
		//			thread.join();
		//		} catch (InterruptedException e) {
		//
		//		}
		//		System.out.println("Generated primes: " + primes1);
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		AbstractTaskRunnerGoodCancel atrgc = new AbstractTaskRunnerGoodCancel(es);
		atrgc.timedRun(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "::: says : " + i);
				if (i == 2) {
					int x = 5 / 0;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, 5, TimeUnit.SECONDS);
	}
}
