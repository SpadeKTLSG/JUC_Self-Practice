package com.juc.basic;

import org.junit.Test;

public class MyJUCTest {


    @Test
    public void runnableTest() throws InterruptedException {
        //single thread
        Thread thread = new Thread(new MyRunnable("Come on!"));
        thread.start();
        thread.join(); // -> main thread wait for this thread to finish, or nothing would happened :)

        //batch processing, but wait between each thread when generate
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            new Thread(new MyRunnable("No." + i)).start();
        }
    }

}
