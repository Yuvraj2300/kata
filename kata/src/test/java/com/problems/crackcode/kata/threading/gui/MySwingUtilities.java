package com.problems.crackcode.kata.threading.gui;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class MySwingUtilities {
    private final static ExecutorService es = Executors.newSingleThreadExecutor(new MySwingThread());
    private static volatile Thread swingThread;

    private static class MySwingThread implements ThreadFactory {
        @Override
        public Thread newThread(@NotNull Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    public static void invokeLater(Runnable task) {
        es.submit(task);
    }

    public static void invokeAndWait(Runnable task) {
        Future<?> submit = es.submit(task);
        try {
            submit.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
