package com.problems.crackcode.kata.threading.gui;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class MySwingUtilities {
    private static Thread swingThread;
    private static ExecutorService es = Executors.newSingleThreadExecutor(new SingleThreadFactory());

    private static class SingleThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(@NotNull Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    //fire and forget
    public static void invokeLater(Runnable task) {
        es.execute(task);
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
