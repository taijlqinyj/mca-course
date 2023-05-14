package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ClassName: T07_BlockingQueue
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26。
 * 两个线程交替运行
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T07_BlockingQueue {

    private static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    private static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() -> {
            for (char c : cW){
                System.out.print(c);
                try {
                    queue1.put("OK");
                    queue2.take(); // 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : cN){
                try {
                    queue1.take(); // 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    queue2.put("OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
