package com.juc;

import com.juc.cowork.AwaitSignalExample;
import com.juc.cowork.JoinExample;
import com.juc.cowork.WaitNotifyExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author SK
 * @date 2024/05/18
 */
@Slf4j
public class CoworkTest {


    /**
     * 测试 join() 方法 - 等待线程终止
     */
    @Test
    public void joinTest1() {
        JoinExample example = new JoinExample();
        example.test();
    }

    /**
     * 测试 wait() 和 notify() 方法
     */
    @Test
    public void waitNotifyTest() {
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建一个可缓存的线程池
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(example::after);
        executorService.execute(example::before);
    }

    /**
     * 测试 await() 和 signal() 方法
     */
    @Test
    public void awaitSignalTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();
        executorService.execute(example::after);
        executorService.execute(example::before);
    }
}
