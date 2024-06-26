package com.juc.basic;

import lombok.Data;

/**
 * Runnable实现 线程
 */
@Data
public class MyRunnable implements Runnable {

    /**
     * 唯一id
     */
    public int id; //K, by V -> hash()

    /**
     * 线程名称
     */
    public String name;

    /**
     * 线程包含Data
     */
    public String data; //V

    public MyRunnable(String name) {
        this.name = name;
        this.data = java.time.LocalTime.now().toString();
        this.id = data.hashCode();
    }

    @Override
    public void run() {
        System.out.println((name + " [ " + id + " ] says: " + data)); //run调用, 手动打印
    }

}
