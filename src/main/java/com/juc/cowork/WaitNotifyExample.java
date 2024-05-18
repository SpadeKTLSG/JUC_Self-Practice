package com.juc.cowork;

/**
 * wait() 和 notify() 方法
 */
public class WaitNotifyExample {


    public synchronized void before() {
        System.out.println("before");
        notifyAll(); // 唤醒所有等待线程
    }

    public synchronized void after() {
        try {
            wait(); // 等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }
}
