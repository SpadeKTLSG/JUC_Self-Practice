package com.juc.cowork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignalExample {

    /**
     * 重入锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * Condition: 等待/通知机制
     */
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll(); // 唤醒所有等待线程
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await(); // 等待
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁
        }
    }
}
