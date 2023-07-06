package com.problems.crackcode.kata.threading.executors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyThreadPerTaskExector implements Executor {
	@Override
	public void execute(@NotNull Runnable command) {
		new Thread(command).run();
	}
}
