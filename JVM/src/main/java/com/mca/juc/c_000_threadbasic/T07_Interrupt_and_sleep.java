package com.mca.juc.c_000_threadbasic;


import com.mca.util.SleepHelper;

/**
 * interrupt与sleep() wait() join()
 */
public class T07_Interrupt_and_sleep {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted!");
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.interrupt();
    }
}
