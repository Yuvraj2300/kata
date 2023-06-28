package com.problems.crackcode.kata.threading.synchronizers.futureTask;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class PreLoader {

	FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
		@Override
		public Integer call() throws Exception {
			//			Thread.sleep(10000);
			int result = 0;
			for (int i = 0; i < 1000000; i++) {
				result += i;
			}
			return result;
		}
	});

	private final Thread t = new Thread(futureTask);

	public void start() {
		t.start();
	}

	public Integer getResult() throws InterruptedException {
		Integer result = -1;
		try {
			result = futureTask.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof RuntimeException) {

			}
		}

		return result;
	}


	public static void main(String[] args) {
		PreLoader loader = new PreLoader();
		loader.start();

		Thread t = new Thread();

		try {
			System.out.println(loader.getResult());
		} catch (InterruptedException ex) {

		}
	}
}
