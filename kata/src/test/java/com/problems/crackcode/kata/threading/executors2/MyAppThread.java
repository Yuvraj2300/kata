package com.problems.crackcode.kata.threading.executors2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAppThread extends Thread {

    private static final AtomicInteger alive = new AtomicInteger(0);
    private static final AtomicInteger created = new AtomicInteger(0);

    public static final String DEFAULT_NAME = "MyAppThread-";
    private static final Logger log = Logger.getAnonymousLogger();
    private static volatile boolean debugLifecycle = false;

    public MyAppThread(Runnable task) {
        this(task, DEFAULT_NAME);
    }

    public MyAppThread(Runnable task, String name) {
        super(task, name + created.getAndIncrement());
        setUncaughtExceptionHandler((t, e) -> {
            log.log(Level.SEVERE, "The thread died, UNCAUGHT in thread : " + getName(), e);
        });
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        try {
            if (debug) {
                log.log(Level.FINE, "Creating :" + getName());
            }
            alive.incrementAndGet();
            super.run();
        } finally {
            if (debug) {
                log.log(Level.FINE, "Exiting :" + getName());
            }
            alive.decrementAndGet();
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
