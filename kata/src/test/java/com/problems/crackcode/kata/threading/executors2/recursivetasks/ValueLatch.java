package com.problems.crackcode.kata.threading.executors2.recursivetasks;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class ValueLatch<T> {
    private T value;
    private CountDownLatch latch = new CountDownLatch(1);

    public boolean isSet() {
        return latch.getCount() == 0 ? true : false;
    }

    public void setValue(T value) {
        synchronized (this) {
            if (!isSet()) {
                this.value = value;
                latch.countDown();
            }
        }
    }


    public T getValue() throws InterruptedException {
        latch.await();
        synchronized (this) {
            return value;
        }
    }
}
