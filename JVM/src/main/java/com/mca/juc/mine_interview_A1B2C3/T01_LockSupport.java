package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: T00_Question
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: �����̣߳�һ���߳����1234...26��һ���߳����ABCD...Z
 * ������A1B2C3...Z26
 * �����߳̽�������
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
                LockSupport.unpark(t2); // ����t2
                LockSupport.park(); // �����Լ�
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : cN){
                LockSupport.park(); // �����Լ�
                System.out.println(c);
                LockSupport.unpark(t1); // ����t1
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
