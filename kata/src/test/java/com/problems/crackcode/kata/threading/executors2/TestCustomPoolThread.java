package com.problems.crackcode.kata.threading.executors2;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.*;

public class TestCustomPoolThread {

    public static void main(String[] args) {
        //@formatter:off
        ExecutorService exSvc = new TimingThreadPool(2, 2, 2, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(@NotNull Runnable r) {
                    return new MyAppThread(r);
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy());
        //@formatter:on


        ConcurrentHashMap<Long, Future<Long>> ipToOp = new ConcurrentHashMap<>();

        List<Long> lst = Arrays.asList(4L, 2L, 1L, 5L, 33L, 2L, 12L, 1L);

        for (long i : lst) {
            ipToOp.put(i, exSvc.submit(new SandboxFactorialTask(i)));
        }

        while (!ipToOp.isEmpty()) {
            for (Map.Entry<Long, Future<Long>> entry : ipToOp.entrySet()) {
                if (entry.getValue().isDone()) {
                    Long key = entry.getKey();
                    Future<Long> removedAndDone = ipToOp.remove(key);
                    try {
                        Long i = removedAndDone.get();
                        System.out.println("OP : --------------------> Input :" + key + " Output: " + i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


        List<Runnable> runnables = exSvc.shutdownNow();
        System.out.println("Runnables impacted or not run due to shutdown  :" + runnables.size() + runnables);
    }


}


class SandboxFactorialTask implements Callable<Long> {

    private long x;

    public SandboxFactorialTask(long x) {
        this.x = x;
    }


    private Long factorial(long x) {
        if (x == 0) return 1L;

        return x * factorial(x - 1);
    }

    @Override
    public Long call() throws Exception {
        return factorial(x);
    }
}
