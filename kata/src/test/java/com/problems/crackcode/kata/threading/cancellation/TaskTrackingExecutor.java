package com.problems.crackcode.kata.threading.cancellation;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class TaskTrackingExecutor extends AbstractExecutorService {
	ExecutorService es;
	Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());


	@NotNull
	@Override
	public List<Runnable> shutdownNow() {
		return null;
	}

	@Override
	public void shutdown() {
		es.shutdown();
	}

	@Override
	public boolean isShutdown() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void execute(@NotNull Runnable command) {
		execute(new Runnable() {
			@Override
			public void run() {
				try {
					command.run();
				} finally {
					if (isShutdown() && Thread.currentThread().isInterrupted()) {
						tasksCancelledAtShutdown.add(command);
					}
				}
			}
		});
	}
}
