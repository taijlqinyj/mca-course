package com.mca.jvm.gc;

/**
 * ClassName: T01_HelloGC
 * Package: com.mca.jvm.gc
 * Description: Linux 系统运行
 * -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -XX:+PrintVMOptions -
 *
 * @Author: yujie.qin
 * @Create: 2023/3/14 - 18:32
 * @version: v1.0
 */
public class T01_HelloGC {
    public static void main(String[] args) {

        for(int i=0; i<10000; i++) {
            byte[] b = new byte[1024 * 1024];
        }
    }
}
