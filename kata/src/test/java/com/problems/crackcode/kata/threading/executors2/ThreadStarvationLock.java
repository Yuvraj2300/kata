package com.problems.crackcode.kata.threading.executors2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadStarvationLock {
    public static void main(String[] args) {
        System.out.println("Executing in thread : " + Thread.currentThread().getName());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing in thread : " + Thread.currentThread().getName());
                try {
                    int sum = es.submit(() -> {
                        System.out.println("Executing in thread : " + Thread.currentThread().getName());
                        return Math.toIntExact((long) Math.pow(2, 2));
                    }).get() + 1;
                    System.out.println("Executing in thread : " + Thread.currentThread().getName());
                    System.out.println("Sum was : " + sum);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
