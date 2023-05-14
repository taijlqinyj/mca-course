package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * ClassName: T11_TransferQueue
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: �����̣߳�һ���߳����1234...26��һ���߳����ABCD...Z
 * ������A1B2C3...Z26��
 * �����߳̽�������
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T11_TransferQueue {
    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                for (char c : cW){
                    transferQueue.transfer(c);
                    System.out.print(transferQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                for (char c : cN){
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
