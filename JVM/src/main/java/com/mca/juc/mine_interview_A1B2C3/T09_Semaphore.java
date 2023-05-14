package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.Semaphore;

/**
 * ClassName: T09_Semaphore
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26。
 * 两个线程交替运行 -- Semaphore 信号灯实现
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T09_Semaphore {

    static Semaphore semaphoreA = new Semaphore(1);
    static Semaphore semaphore1 = new Semaphore(1);

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        try {
            semaphore1.acquire(); // 获取数字的信号灯，让字母的信号灯先执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            try {
                semaphoreA.acquire();
                for (char c : cW){
                    System.out.print(c);
                    semaphore1.release();
                    semaphoreA.acquire();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放数字的信号灯，让程序结束
                semaphore1.release();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                semaphore1.acquire();
                for (char c : cN){
                    System.out.print(c);
                    semaphoreA.release();
                    semaphore1.acquire();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放数字的信号灯，让程序结束
                semaphore1.release();
            }
        }, "t2").start();
    }
}
