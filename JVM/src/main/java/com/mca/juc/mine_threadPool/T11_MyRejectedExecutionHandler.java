package com.mca.juc.mine_threadPool;

import java.util.concurrent.*;

/**
 * ClassName: T11_MyRejectedExecutionHandler
 * Package: com.mca.juc.mine_threadPool
 * Description: 自定义拒绝策略
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 11:38
 * @version: v1.0
 */
public class T11_MyRejectedExecutionHandler {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4,4,60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                new MyRejectedExecutionHandler());
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r to kafka, mysql, redis
            //try 3 times
            if (executor.getQueue().size() < 10000) {
                //try put again();
            }
        }
    }
}
