package com.mca.juc.mine_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: T08_CachedThreadPool
 * Package: com.mca.juc.mine_threadPool
 * Description: 核心线程数是0，最大线程数是Integer.MAX_VALUE
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 11:32
 * @version: v1.0
 */
public class T08_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);

        System.out.println(service);
        service.shutdown();
        System.out.println(service);
    }
}
