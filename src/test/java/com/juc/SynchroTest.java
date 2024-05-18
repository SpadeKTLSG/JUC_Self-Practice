package com.juc;

import com.juc.synchro.SynchronizedExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SynchroTest {

    /**
     * 测试 synchronized 关键字 - **同步一个代码块**
     */
    @Test
    public void synchronizedTest1() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample(); //只作用于同一个对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e1::func1);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于是同一个对象，所以会互斥
    }

    /**
     * 测试 synchronized 关键字 - **同步不同代码块**
     */
    @Test
    public void synchronizedTest2() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e2::func1);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于是不同对象，所以不会互斥
    }

    /**
     * 测试 synchronized 关键字 - **同步一个方法**
     */
    @Test
    public void synchronizedTest3() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func2);
        executorService.execute(e1::func2);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于是同一个方法，所以会互斥
    }


    /**
     * 测试 synchronized 关键字 - **同步一个方法**
     */
    @Test
    public void synchronizedTest31() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func2);
        executorService.execute(e2::func2);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于不是同一个方法，所以不会互斥
    }

    /**
     * 测试 synchronized 关键字 - **同步一个类**
     */
    @Test
    public void synchronizedTest4() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func3);
        executorService.execute(e2::func3);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于是同一个类，所以会互斥
    }


    /**
     * 测试 synchronized 关键字 - **同步一个静态方法**
     */
    @Test
    public void synchronizedTest5() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SynchronizedExample::fun);
        executorService.execute(SynchronizedExample::fun);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
        // 由于是静态方法(单个)，所以会互斥
    }


    // ------------------------------------------------------------

    /**
     * 测试 ReentrantLock 重入锁
     */
    @Test
    public void reentrantLockTest() throws InterruptedException {
        com.juc.synchro.ReentrantLockExample e1 = new com.juc.synchro.ReentrantLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func);
        executorService.execute(e1::func);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕
    }

}
