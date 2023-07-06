package com.problems.crackcode.kata.threading.executors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class MySynchronousExecutor implements Executor {
	@Override
	public void execute(@NotNull Runnable command) {
		command.run();
	}
}
