package com.mca.jvm.jmm;

/**
 * ClassName: T001_CacheLinePadding
 * Package: com.mca.jvm.jmm
 * Description: 伪共享问题 ==> 伪共享小程序证明
 * 没有使用 cache line 对齐，cache line 64bits
 * 两个数据在同一个缓存行，导致两个线程修改时
 * CPU 缓存一致性协议（MESI）中的M和I来回切换
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 18:13
 * @version: v1.0
 */
public class T001_CacheLinePadding {
    private static class T {
        public volatile long x = 0L;
    }

    private static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
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
