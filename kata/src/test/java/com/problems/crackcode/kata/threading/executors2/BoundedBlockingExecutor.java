package com.problems.crackcode.kata.threading.executors2;

import java.util.concurrent.*;

public class BoundedBlockingExecutor {
    Executor executor;
    Semaphore semaphore;

    public BoundedBlockingExecutor(Executor executor, Semaphore semaphore) {
        this.executor = executor;
        this.semaphore = semaphore;
    }

    public void submitTask(Runnable runnable) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(() -> {
                try {
                    runnable.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException ex) {
            semaphore.release();
        }
    }


    <T> Future<T> submitTask(Callable<T> callable) throws InterruptedException {
        semaphore.acquire();

        FutureTask<T> ft = new FutureTask<>(callable);

        Future<T> toReturn = null;
        ft.run();
        toReturn = ft;

        semaphore.release();

        return toReturn;
    }

}
