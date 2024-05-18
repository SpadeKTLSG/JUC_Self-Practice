package com.juc;

import com.juc.javaconc.ThreadUnsafeExample;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java并发测试
 */
public class JavaconcTest {

    /**
     * 测试线程不安全的例子
     */
    @Test
    public void unsafeTest() throws InterruptedException {
        //同时用1000个线程对cnt进行++操作
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
        //结果: 996! 什么因果报应!
    }
}
