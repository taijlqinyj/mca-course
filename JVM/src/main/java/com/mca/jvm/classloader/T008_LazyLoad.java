package com.mca.jvm.classloader;

/**
 * ClassName: T008_LazyLoad
 * Package: com.mca.jvm.classloader
 * Description: JVM懒加载机制，按需加载
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 10:39
 * @version: v1.0
 */
public class T008_LazyLoad {

    static {
        System.out.println("LazyLoad static code");
    }

    public static void main(String[] args) {
        new Demo1();
        System.out.println("==========================");
        Demo2 demo2 = null;
    }

    static class Demo1 {
        static {
            System.out.println("Demo1 static code");
        }

        public Demo1() {
            System.out.println("Init Demo1");
        }
    }

    static class Demo2 {
        static {
            System.out.println("Demo2 static code");
        }

        public Demo2() {
            System.out.println("Init Demo2");
        }
    }
}
