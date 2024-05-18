package com.juc;


import com.juc.basic.MyRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池
 */
@Slf4j
public class PoolTest {

    /**
     * 测试 Executors 线程池
     */
    @Test
    public void ExecutorsTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable("hhh"));
        }
        executorService.shutdown();
    }

    /**
     * executeRunnableTasks - Executes runnable tasks using ExecutorService
     */
    @Test
    public void executeRunnableTasks() {


        //Executes only one thread
        ExecutorService es1 = Executors.newSingleThreadExecutor();

        //Internally manages thread pool of 2 threads
        ExecutorService es2 = Executors.newFixedThreadPool(2);

        //Internally manages thread pool of 10 threads to run scheduled tasks
        ExecutorService es3 = Executors.newScheduledThreadPool(10);


        ExecutorService executorService = new ThreadPoolExecutor(10, 100, 5L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        //Demo task
        Runnable runnableTask = () -> { //这是一个Runnable对象，用于执行任务
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("Current time :: " + LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //Executor service instance to execute the task
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //1. execute task using execute() method
        executor.execute(runnableTask);

        //2. execute task using submit() method
        Future<String> result = executor.submit(runnableTask, "DONE");

        while (!result.isDone()) {
            try {
                System.out.println("The method return value : " + result.get());
                break;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            //Sleep for 1 second
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Shut down the executor service
        executor.shutdownNow();
    }

    /**
     * Execute Callable tasks
     */
    @Test
    public void executeCallableTasks() {
        //Demo Callable task
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(1000);
            return "Current time :: " + LocalDateTime.now();
        };

        //Executor service instance
        ExecutorService executor = Executors.newFixedThreadPool(1);

        List<Callable<String>> tasksList = Arrays.asList(callableTask, callableTask, callableTask);

        //1. execute tasks list using invokeAll() method
        try {
            List<Future<String>> results = executor.invokeAll(tasksList);

            for (Future<String> result : results) {
                System.out.println(result.get());
            }

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        //2. execute individual tasks using submit() method
        Future<String> result = executor.submit(callableTask);

        while (!result.isDone()) {
            try {
                System.out.println("The method return value : " + result.get());
                break;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            //Sleep for 1 second
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Shut down the executor service
        executor.shutdownNow();
    }
}





