package com.mca.juc.mine_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: T02_ExecutorService
 * Package: com.mca.juc.mine_threadPool
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 20:10
 * @version: v1.0
 */
public class T02_ExecutorService {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

    }
}
