package com.mca.juc.mine_interview_A1B2C3;

import java.util.concurrent.Exchanger;

/**
 * ClassName: T10_Exchanger
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: �����̣߳�һ���߳����1234...26��һ���߳����ABCD...Z
 * ������A1B2C3...Z26��
 * �����߳̽�������
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T10_Exchanger {

    static Exchanger<Character> exchangerA = new Exchanger<>();
    static Exchanger<Character> exchanger1 = new Exchanger<>();

    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() -> {
            try {
                for (char c : cW){
                    exchangerA.exchange(c);
                    System.out.print(exchanger1.exchange(c));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (char c : cN){
                    System.out.print(exchangerA.exchange(c));
                    exchanger1.exchange(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
