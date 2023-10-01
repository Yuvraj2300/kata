package com.problems.crackcode.kata.threading.executors2;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class ThreadFactories {

    Executor executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(Integer.MAX_VALUE), new ThreadFactory() {
        @Override
        public Thread newThread(@NotNull Runnable r) {
            return new Thread(r);
        }
    }, new ThreadPoolExecutor.CallerRunsPolicy());

}
