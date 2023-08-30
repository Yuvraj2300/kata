package com.problems.crackcode.kata.threading.cancellation;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public interface CancellableType<T> extends Callable<T> {
	void cancel();

	RunnableFuture<T> newTask();
}
