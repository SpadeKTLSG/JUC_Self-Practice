package com.juc.basic;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * Callable实现 线程
 */
@Data
public class MyCallable implements Callable<String> {

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


    public MyCallable(String name) {
        this.name = name;
        this.data = java.time.LocalTime.now().toString();
        this.id = data.hashCode();
    }

    @Override
    public String call() {
        return (name + " [ " + id + " ] says: " + data); //call调用, 自动返回内容
    }

}
