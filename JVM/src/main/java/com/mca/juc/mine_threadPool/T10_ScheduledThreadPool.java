package com.mca.juc.mine_threadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: T10_ScheduledThreadPool
 * Package: com.mca.juc.mine_threadPool
 * Description: 专门用来执行定时任务的
 * 定时器框架：quartz,cron --> 复杂场景
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 11:36
 * @version: v1.0
 */
public class T10_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 2000, TimeUnit.MILLISECONDS);

    }
}
