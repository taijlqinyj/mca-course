package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: T00_Question
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26
 * 两个线程交替运行
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        t1 = new Thread(() -> {
            for (char c : cW){
                System.out.println(c);
                LockSupport.unpark(t2); // 唤醒t2
                LockSupport.park(); // 阻塞自己
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : cN){
                LockSupport.park(); // 阻塞自己
                System.out.println(c);
                LockSupport.unpark(t1); // 唤醒t1
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
