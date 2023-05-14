package com.mca.jvm.fiber;

/**
 * ClassName: HelloFiber
 * Package: com.mca.jvm.fiber
 * Description: 纤程，借助第三方纤程库，Agent 机制，premain，Instrumentation
 * <dependency>
 *     <groupId>co.paralleluniverse</groupId>
 *     <artifactId>quasar-core</artifactId>
 *     <version>0.8.0</version>
 * </dependency>
 *
 * @Author: yujie.qin
 * @Create: 2023/3/16 - 14:06
 * @version: v1.0
 */
public class HelloFiber {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    calc();
                }
            });
            thread.start();

/*            Fiber<Void> fiber = new Fiber<Void>(new SuspendableRunnable() {
                public void run() throws SuspendExecution, InterruptedException {
                    calc();
                }
            });
            fiber.start();*/
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static void calc(){
        int result = 0;
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 200; j++) {
                result += j;
            }
        }
    }
}
