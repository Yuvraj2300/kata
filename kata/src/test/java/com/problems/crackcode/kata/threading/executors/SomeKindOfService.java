package com.problems.crackcode.kata.threading.executors;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class SomeKindOfService<T> implements Runnable {
	ExecutorService es;
	Callable<T> command;

	public SomeKindOfService(Callable<T> command) {
		this.es = Executors.newFixedThreadPool(2);
		this.command = command;
	}

	@Override
	public void run() {
		Future<T> submit = es.submit(command);
		try {
			submit.get();
		} catch (InterruptedException e) {

		} catch (ExecutionException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	public void stop() throws InterruptedException {
		es.shutdown();
		if (!es.awaitTermination(60, TimeUnit.SECONDS)) {
			es.shutdownNow();
		}
	}
}
