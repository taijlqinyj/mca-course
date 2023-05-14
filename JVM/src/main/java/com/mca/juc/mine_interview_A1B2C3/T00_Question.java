package com.mca.juc.mine_interview_A1B2C3;

/**
 * ClassName: T00_Question
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26。
 * 两个线程交替运行
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T00_Question {
    public static void main(String[] args) {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        new Thread(() -> {
            for (char c : cW){

            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : cN){

            }
        }, "t2").start();
    }
}
