package com.yj;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者
 */
public class BounderBuffer {
    final Lock lock = new ReentrantLock();
    final Condition full = lock.newCondition();
    final Condition empty = lock.newCondition();
    final Object[] items = new Object[100];
    private int putptr;
    private int takeptr;
    private int count;

    public void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            if (count == items.length) {
                full.await();
            }
            items[putptr] = obj;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                empty.await();
            }
            Object obj = items[takeptr++];
            if (takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            full.signal();
            return obj;

        } finally {
            lock.unlock();
        }
    }
}
