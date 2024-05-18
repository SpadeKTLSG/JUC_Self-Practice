package com.juc.basic;

import lombok.EqualsAndHashCode;

/**
 * MyThread实现 线程
 */
//这里用lombok的Data会和Thread的方法冲突
//java: com.juc.basic.MyThread中的setName(java.lang.String)无法覆盖java.lang.Thread中的setName(java.lang.String)
//  被覆盖的方法为final
//出现双方重名问题. 于是报错
@EqualsAndHashCode(callSuper = true)
public class MyThread extends Thread {

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

    public MyThread(String name) {
        this.name = name;
        this.data = java.time.LocalTime.now().toString();
        this.id = data.hashCode();
    }

    public void run() {
        System.out.println((name + " [ " + id + " ] says: " + data)); //Thread调用, 手动打印
    }

}
