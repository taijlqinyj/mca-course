package com.mca.juc.c_001_03_Ordering;

/**
 * ClassName: T02_NoVisibility
 * Package: com.mca.juc.c_001_03_Ordering
 * Description: 可见性程序
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 9:13
 * @version: v1.0
 */
public class T02_NoVisibility {
    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new ReaderThread();
        t.start();
        number = 42;
        ready = true;
        t.join();
    }
}
