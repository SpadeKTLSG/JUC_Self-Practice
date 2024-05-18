package com.juc.utils;

import java.util.concurrent.CountDownLatch;

public class threadUtils {
    //CountDownLatch可以进行阻塞, 来观察两个线程的启动
    public static CountDownLatch countDownLatch = new CountDownLatch(2);
}
