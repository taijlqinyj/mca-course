package com.mca.jvm.gc;

/**
 * ClassName: LambdaGC
 * Package: com.mca.jvm.gc
 * Description: -XX:MaxMetaspaceSize=5M -XX:+PrintGCDetails
 *
 * @Author: yujie.qin
 * @Create: 2023/3/15 - 15:26
 * @version: v1.0
 */
public class LambdaGC {
    public static void main(String[] args) {
        for(;;) {
            I i = C::n;
        }
    }

    public static interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }
}
