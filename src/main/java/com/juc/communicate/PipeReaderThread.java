package com.juc.communicate;

import java.io.PipedReader;


/**
 * 管道读者
 *
 * @author SK
 * @date 2024/05/18
 */
public class PipeReaderThread implements Runnable {

    /**
     * 管道流
     */
    PipedReader pr;

    String name;

    public PipeReaderThread(String name, PipedReader pr) {
        this.name = name;
        this.pr = pr;
    }

    public void run() {
        try {
            // continuously read data from stream and print it in console
            while (true) {
                char c = (char) pr.read(); // read a char
                if (c != -1) { // check for -1 indicating end of file
                    System.out.print(c);
                }
            }
        } catch (Exception e) {
            System.out.println(" PipeThread Exception: " + e);
        }
    }
}
