package com.mca.juc.mine_threadPool;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: T06_01_CompletableFuture
 * Package: com.mca.juc.mine_threadPool
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/20 - 22:13
 * @version: v1.0
 */
public class T06_01_CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start, end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfJD);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM);

        CompletableFuture.anyOf(futureTM, futureJD, futureTB).join();
        System.out.println(futureJD.get());
        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));


    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
