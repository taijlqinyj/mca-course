package com.mca.jvm.jmm;

/**
 * ClassName: T002_CacheLinePadding
 * Package: com.mca.jvm.jmm
 * Description: 伪共享问题 ==> 伪共享小程序证明
 * 使用 cache line 对齐
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 18:13
 * @version: v1.0
 */
public class T002_CacheLinePadding {
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;
    }

    private static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000L);
    }
}
