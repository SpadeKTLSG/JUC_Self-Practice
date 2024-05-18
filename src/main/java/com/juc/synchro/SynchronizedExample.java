package com.juc.synchro;


/**
 * synchronized 关键字
 */
public class SynchronizedExample {

    /**
     * 同步一个代码块
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }


    /**
     * 同步一个方法
     */
    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }

    }


    /**
     * 同步一个类
     */
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 同步一个静态方法
     */
    public synchronized static void fun() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }
}
