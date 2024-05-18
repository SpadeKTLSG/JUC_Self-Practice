package com.juc;

import com.juc.communicate.PipeReaderThread;
import com.juc.communicate.PipeWriterThread;
import org.junit.Test;

import java.io.PipedReader;
import java.io.PipedWriter;

public class CommunicateTest {


    @Test
    public void PipedCommunicationTest() {


        try {
            // Create writer and reader instances
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter();

            // Connect the writer with reader
            pw.connect(pr);

            // Create one writer thread and one reader thread
            Thread thread1 = new Thread(new PipeReaderThread("ReaderThread", pr));

            Thread thread2 = new Thread(new PipeWriterThread("WriterThread", pw));

            // start both threads
            thread1.start();
            thread2.start();

        } catch (Exception e) {
            System.out.println("PipeThread Exception: " + e);
        }

    }

    @Test
    public void BlockQueueTest() {
/*        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);

        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(10,
                20, 5000, TimeUnit.MILLISECONDS, blockingQueue);

        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r,
                                          ThreadPoolExecutor executor) {
                System.out.println("DemoTask Rejected : "
                        + ((DemoTask) r).getName());
                System.out.println("Waiting for a second !!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lets add another time : "
                        + ((DemoTask) r).getName());
                executor.execute(r);
            }
        });
        // Let start all core threads initially
        executor.prestartAllCoreThreads();
        while (true) {
            threadCounter++;
            // Adding threads one by one
            System.out.println("Adding DemoTask : " + threadCounter);
            executor.execute(new DemoTask(threadCounter.toString()));

            if (threadCounter == 100)
                break;
        }
    }*/
    }
}
