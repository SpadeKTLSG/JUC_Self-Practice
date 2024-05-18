package com.juc.cowork;

/**
 * join() 方法 - 等待线程终止
 */
public class JoinExample {

    /**
     * A线程
     */
    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    /**
     * B线程, 持有A线程的引用
     */
    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join(); //先让A进来, 屋里冷, 然后等A出去了, B再进来
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }


    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
