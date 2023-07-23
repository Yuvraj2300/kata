package com.problems.crackcode.kata.threading.executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

class Tester {
	@Test
	@DisplayName("Test Task Percentage Calculator")
	void testTaskPercentageCalculator() {
		ExecutorService exSvc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		List<Callable<Integer>> tasks = new ArrayList<>();
		tasks.add(() -> {
			Thread.sleep(3000);
			return 3 * 2;
		});
		tasks.add(() -> {
			Thread.sleep(6000);
			return 5 + 7;
		});
		tasks.add(() -> {
			Thread.sleep(1000);
			return 9 - 4;
		});
		TaskPercentageCalc<Integer> taskDoer = new TaskPercentageCalc<>(exSvc, tasks, 0);
		List<Integer> computes = taskDoer.compute();
		System.out.println(computes);
	}

}

public class TaskPercentageCalc<T> {

	final T defaultFallback;

	final ExecutorService exSvc;
	final List<Callable<T>> givenTasks;

	final CompletionService<T> complSvc;

	public TaskPercentageCalc(ExecutorService exSvc, List<Callable<T>> givenTasks, T defaultFallback) {
		this.exSvc = exSvc;
		this.givenTasks = new ArrayList<>(givenTasks);
		this.defaultFallback = defaultFallback;
		this.complSvc = new ExecutorCompletionService<>(exSvc);
	}

	public List<T> compute() {
		List<T> returnValues = new LinkedList<>();
		for (int i = 0; i < givenTasks.size(); i++) {
			complSvc.submit(givenTasks.get(i));
		}

		for (int i = 0; i < givenTasks.size(); i++) {
			try {
				Future<T> take = complSvc.take();
				try {
					T computeOp = take.get(5, TimeUnit.SECONDS);
					returnValues.add(computeOp);
					System.out.println("%age Completed : " + ((double) i / givenTasks.size()) * 100 + " %");
				} catch (TimeoutException e) {
					System.out.println("Compute Timed out , hence adding default Value & cancelling the task");
					returnValues.add(defaultFallback);
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}

		if (returnValues.size() == givenTasks.size()) {
			System.out.println("%age Completed : " + ((double) returnValues.size() / givenTasks.size()) * 100 + " %");
		}

		_tearDown();

		return returnValues;
	}

	private void _tearDown() {
		this.exSvc.shutdown();
	}

}
