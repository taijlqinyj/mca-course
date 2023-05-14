package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: T06_AtomicInteger
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: �����̣߳�һ���߳����1234...26��һ���߳����ABCD...Z
 * ������A1B2C3...Z26
 * �����߳̽�������
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T06_AtomicInteger {

    private static final AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() -> {
            for (char c : cW){
                while (threadNo.get() != 1){}
                System.out.print(c);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : cN){
                while (threadNo.get() != 2){}
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }

}
