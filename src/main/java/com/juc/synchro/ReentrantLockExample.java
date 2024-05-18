package com.juc.synchro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 重入锁
 */
public class ReentrantLockExample {

    /**
     * 重入锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 同步一个代码块
     */
    public void func() {
        lock.lock();

        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁
        }
    }
}
