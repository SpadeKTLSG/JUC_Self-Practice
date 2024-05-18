package com.juc.communicate;

import java.io.PipedWriter;


/**
 * 管道写者
 *
 * @author SK
 * @date 2024/05/18
 */
public class PipeWriterThread implements Runnable {

    /**
     * 管道流
     */
    PipedWriter pw;

    String name;

    public PipeWriterThread(String name, PipedWriter pw) {
        this.name = name;
        this.pw = pw;
    }

    public void run() {
        try {
            while (true) {
                // Write some data after every two seconds
                pw.write("Testing data written...n");
                pw.flush();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println(" PipeThread Exception: " + e);
        }
    }
}
