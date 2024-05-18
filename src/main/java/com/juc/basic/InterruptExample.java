package com.juc.basic;


/**
 * 线程中断
 *
 * @author SK
 * @date 2024/05/18
 */
public class InterruptExample {

    /**
     * 线程强制中断
     */
    public static class MyThread1 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程自行中断
     */
    public static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (!interrupted()) { //判断是否中断
                System.out.println("Thread running");
            }
            System.out.println("Thread end");
        }
    }
}
