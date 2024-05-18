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
    public void synchronizedTest() throws InterruptedException {
        SynchronizedExample e1 = new SynchronizedExample(); //只作用于同一个对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e1::func1);
        executorService.shutdown(); // 不再接受新任务，但已提交的任务会继续执行
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务执行完毕

    }
}
