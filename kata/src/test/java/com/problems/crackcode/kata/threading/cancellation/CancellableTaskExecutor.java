package com.problems.crackcode.kata.threading.cancellation;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class CancellableTaskExecutor extends ThreadPoolExecutor {
	public CancellableTaskExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableType) {
			return ((CancellableType<T>) callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}
}
