package com.mca.juc.mine_threadPool;

import java.util.concurrent.*;

/**
 * ClassName: T03_Callable
 * Package: com.mca.juc.mine_threadPool
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 20:12
 * @version: v1.0
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };


        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable); // Òì²½

        System.out.println(future.get()); // ×èÈû
        service.shutdown();
    }
}
