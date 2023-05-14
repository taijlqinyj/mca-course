package com.mca.juc.mine_threadPool;

import java.util.concurrent.Executor;

/**
 * ClassName: T01_MyExecutor
 * Package: com.mca.juc.mine_threadPool
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 20:07
 * @version: v1.0
 */
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        T01_MyExecutor myExecutor = new T01_MyExecutor();
        myExecutor.execute(() -> {
            System.out.println("Mine Executor");
        });
    }
}
