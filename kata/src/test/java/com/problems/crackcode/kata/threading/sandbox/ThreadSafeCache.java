package com.problems.crackcode.kata.threading.sandbox;

import java.util.HashMap;
import java.util.concurrent.*;

public class ThreadSafeCache<K, V> {
    ConcurrentHashMap<K, Future<V>> hm = new ConcurrentHashMap<>();

    public V getValue(K key) {
        try {
            return checkCache(key).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    Future<V> checkCache(K key) {
        Future<V> f = hm.get(key);
        if (f == null) {
            FutureTask<V> ft = computeValue(key);
            f = hm.putIfAbsent(key, ft);
            if (f == null) {
                f = ft;
                ft.run();
            }
        }

        return f;
    }


    private FutureTask<V> computeValue(final K key) {
        //do some compute
        FutureTask<V> ft = new FutureTask<>(() -> {
            Thread.sleep(10000);
            return (V) new Object();
        });
        return ft;
    }
}