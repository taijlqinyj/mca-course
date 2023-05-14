package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: T05_CAS
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26
 * 两个线程交替运行
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T05_CAS {

    enum Ready2Run {T1, T2}

    static volatile Ready2Run ready2Run = Ready2Run.T1;

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() -> {
            for (char c : cW){
                while (ready2Run != Ready2Run.T2){}
                System.out.print(c);
                ready2Run = Ready2Run.T1;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : cN){
                while (ready2Run != Ready2Run.T1){}
                System.out.print(c);
                ready2Run = Ready2Run.T2;
            }
        }, "t2").start();
    }

}
