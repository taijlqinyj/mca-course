package com.mca.jvm.classloader;

/**
 * ClassName: T007_WayToRun
 * Package: com.mca.jvm.classloader
 * Description: 编译模式
 * 1、混合模式，混合使用解释器 + 热点代码编译  -Xmixed (VM Options)
 * 2、解释模式：纯解释模式，启动很快，执行稍慢 -Xint (VM Options)
 * 3、编译模式：纯编译模式，启动很慢，执行很快 -Xcomp (VM Options)
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 10:00
 * @version: v1.0
 */
public class T007_WayToRun {
    public static void main(String[] args) {
        for (int i = 0; i < 10_0000; i++) {
            m();
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void m(){
        for (long i = 0; i < 10_0000L; i++) {
            long j = i%3;
        }
    }
}
