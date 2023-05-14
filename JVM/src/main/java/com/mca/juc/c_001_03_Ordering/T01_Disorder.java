package com.mca.juc.c_001_03_Ordering;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName: T01_Disorder
 * Package: com.mca.juc.c_001_03_Ordering
 * Description: 主要验证指令重排序
 * 本程序跟可见性无关，曾经有同学用单核也发现了这一点
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 9:10
 * @version: v1.0
 */
public class T01_Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        for (long i = 0; i < Long.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(2);

            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;

                    latch.countDown();
                }

            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;

                    latch.countDown();
                }
            });
            one.start();
            other.start();
            latch.await();
            String result = "第" + i + "次 (" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            }
        }
    }
}
