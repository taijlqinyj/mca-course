package com.mca.juc.mine_threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: T06_00_Future
 * Package: com.mca.juc.mine_threadPool
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 22:12
 * @version: v1.0
 */
public class T06_00_Future {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        }); // new Callable () { Integer call();}

        new Thread(task).start();

        System.out.println(task.get()); // 阻塞
    }
}
