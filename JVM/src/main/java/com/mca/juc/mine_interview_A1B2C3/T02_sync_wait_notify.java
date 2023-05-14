package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: T00_Question
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26
 * 两个线程交替运行
 * 要求某一个线程先执行，可用volatile变量，CountDownLatch
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T02_sync_wait_notify {
    // 要求某个线程先执行
    private static volatile boolean t1Started = false;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object object = new Object();
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() ->{
            synchronized (object) {
                for (char c : cW){
                    System.out.print(c);
                    latch.countDown();
                    //t1Started = true;
                    try {
                        object.notify(); // 唤醒其他线程，并不释放锁
                        object.wait(); // 让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify(); // 必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(() ->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object) {
//                while (!t1Started){
//                    try {
//                        object.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                for (char c : cN){
                    System.out.print(c);
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "t2").start();

    }

}
