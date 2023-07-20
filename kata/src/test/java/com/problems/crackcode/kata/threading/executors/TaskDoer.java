package com.problems.crackcode.kata.threading.executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class TaskDoer {

	public final ExecutorService esvc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public final CompletionService<Integer> cs = new ExecutorCompletionService<>(esvc);

	@Test
	@DisplayName("TEst Completion Service")
	void tEstCompletionService() {
		process();
	}

	public void process() {
		List<Callable<Integer>> listOfTasks = new ArrayList<>();
		Callable<Integer> task1 = new Callable() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(5000);
				return 4 * 3;
			}
		};
		listOfTasks.add(task1);

		Callable<Integer> task2 = () -> {
			return 3 * 34;
		};

		listOfTasks.add(task2);
		Callable<Integer> task3 = () -> {
			return 89 * 34;
		};

		listOfTasks.add(task3);
		Callable<Integer> task4 = () -> {
			return 3 * 34;
		};
		listOfTasks.add(task4);

		List<Future<Integer>> futures = new ArrayList<>();
		for (Callable<Integer> task : listOfTasks) {
			Future<Integer> submit = cs.submit(task);
			futures.add(submit);
		}

		boolean alldone = false;
		for (Future<Integer> future : futures) {
			try {
				Integer integer = future.get(0, TimeUnit.SECONDS);
				System.out.println("Calculation Received : " + integer);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			} catch (TimeoutException e) {
				throw new RuntimeException(e);
			}
		}

		System.out.println();


		for (int i = 0; i < futures.size(); i++) {
			try {
				Future<Integer> take = cs.take();
				Integer integer = take.get();
				System.out.println("Received Output : " + integer);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}

	}





}
