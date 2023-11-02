package com.problems.crackcode.kata.threading.gui;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class GUIExecutor extends AbstractExecutorService {
    final static GUIExecutor instance = new GUIExecutor();

    private GUIExecutor() {
    }

    public static GUIExecutor getInstance() {
        return instance;
    }

    @Override
    public void execute(@NotNull Runnable command) {
        if (MySwingUtilities.isEventDispatchThread()) {
            command.run();
        } else {
            MySwingUtilities.invokeLater(command);
        }
    }


    @Override
    public void shutdown() {

    }

    @NotNull
    @Override
    public List<Runnable> shutdownNow() {
        return null;
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
}
