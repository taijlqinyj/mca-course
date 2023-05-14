package com.mca.juc.mine_interview_A1B2C3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * ClassName: T08_PipedStream
 * Package: com.mca.juc.mine_interview_A1B2C3
 * Description: 两个线程，一个线程输出1234...26，一个线程输出ABCD...Z
 * 最后输出A1B2C3...Z26。
 * 两个线程交替运行
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 16:57
 * @version: v1.0
 */
public class T08_PipedStream {
    public static void main(String[] args) throws Exception {
        char[] cW = "ABCDEFG".toCharArray();
        char[] cN = "1234567".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";
        new Thread(() -> {
            byte[] buffer = new byte[9];

            try {
                for (char c : cW) {
                    System.out.print(c);
                    output1.write(msg.getBytes());
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c : cN) {
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                    }
                    output2.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
