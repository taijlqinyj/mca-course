package com.mca.juc.mine_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: T07_SingleThreadPool
 * Package: com.mca.juc.mine_threadPool
 * Description: 线程池中只有一个线程，保证任务顺序执行
 * 为什么存在一个线程的线程池？
 * 有任务队列，完整的线程生命周期管理
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 11:31
 * @version: v1.0
 */
public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {

                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
        service.shutdown();
    }
}
