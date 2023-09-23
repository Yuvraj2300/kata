package com.problems.crackcode.kata.threading.executors2;

import java.util.concurrent.*;

public class ConfiguringPools {
    public static void main(String[] args) {
//formatter:off
        ExecutorService es = new ThreadPoolExecutor(
                1,
                1
                , 5L, TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(2)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
//formatter:on
        for (int i = 0; i < 5; i++) {
            es.execute(new Task());
        }

        es.shutdownNow();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("I am running in : " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
