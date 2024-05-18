package com.juc;

import com.juc.basic.InterruptExample;
import com.juc.basic.MyCallable;
import com.juc.basic.MyRunnable;
import com.juc.basic.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

import static com.juc.utils.threadUtils.countDownLatch;
import static java.util.Arrays.asList;

@Slf4j
public class BasicTest {


    /**
     * Runnable测试
     */
    @Test
    public void runnableTest() throws InterruptedException {
        //single thread
        Thread thread = new Thread(new MyRunnable("runnable Coming!"));
        thread.start();
        thread.join(); // -> main thread wait for this thread to finish, or nothing would happened :)

        //batch processing, but wait between each thread when generate
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            new Thread(new MyRunnable("No." + i)).start();
        }
    }

    /**
     * Callable测试
     */
    @Test
    public void callableTest() throws InterruptedException, ExecutionException {
        //single thread
        System.out.println(new MyCallable("callable Coming!").call());

        //batch processing, but wait between each thread when generate
/*        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(new MyCallable("No." + i).call());
        }*/

        // use FutureTask
        MyCallable mc = new MyCallable("use FutureTask");
        FutureTask<String> ft = new FutureTask<>(mc);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println(ft.get());

    }

    /**
     * 线程测试
     */
    @Test
    public void threadTest() throws InterruptedException {
        //single thread
        new MyThread("thread Coming!").start();

        //batch processing, but wait between each thread when generate
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            new MyThread("No." + i).start();
        }
    }

    /**
     * CountDownLatch测试
     */
    @Test
    public void innerClazzTest() {

        //这里双方随机抢占资源, 无法保证谁先谁后
        countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            countDownLatch.countDown();//对countDownLatch进行减一操作
            try {
                countDownLatch.await(); //等待countDownLatch为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1");
        }).start();

        new Thread(() -> {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2");
        }).start();
    }


    /**
     * 线程池测试
     */
    @Test
    public void threadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5); //这里是5个线程的线程池

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.execute(() -> { //execute方法, 传入Runnable接口
                System.out.println(finalI);
            });
        }
    }


    /**
     * 定时器测试
     */
    @Test
    public void timeTest() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("1");
                System.out.println(1);
            }
        }, 2000L, 1000L);
    }


    /**
     * Stream测试
     */
    @Test
    public void streamTest() {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6);

        list.parallelStream().forEach(p -> {

            countDownLatch.countDown(); //将所有请求在打印之前进行阻塞，方便观察
            try {
                System.out.println("线程执行到这里啦");
                Thread.sleep(10000);
                countDownLatch.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p);
        });

    }

    @Test
    public void testInterrupt1() {
        Thread thread1 = new InterruptExample.MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run"); //sleep interrupted
    }

    @Test
    public void testInterrupt2() {
        Thread thread2 = new InterruptExample.MyThread2();
        thread2.start();
        //wait, then kill it!
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();
    }


}
