package com.mca.juc.mine_threadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: T12_WorkStealingPool
 * Package: com.mca.juc.mine_threadPool
 * Description: 每个线程都有自己单独的队列 work queue
 * 采用 work stealing算法
 * 当自己的任务执行完了，从其他线程的任务队列里偷（poll）
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 13:53
 * @version: v1.0
 */
public class T12_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000)); //daemon
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + Thread.currentThread().getName());

        }

    }
}
