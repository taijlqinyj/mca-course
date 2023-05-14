package com.mca.jvm.jmm;

import com.mca.agent.ObjectSizeAgent;

/**
 * ClassName: T005_ObjectSizeAgent
 * Package: com.mca.jvm.agent
 * Description: 使用 JavaAgent 测试 Object 的大小
 *
 * @Author: yujie.qin
 * @Create: 2023/3/13 - 10:03
 * @version: v1.0
 */
public class T005_ObjectSizeAgent {

    public static void main(String[] args) {
        System.out.println("Object 的长度：" + ObjectSizeAgent.sizeOf(new Object()));
        System.out.println("数组的长度：" + ObjectSizeAgent.sizeOf(new int[] {}));
        System.out.println("自定义Object 的长度：" + ObjectSizeAgent.sizeOf(new P()));
    }

    // 一个 Object 占多少个字节
    // -XX:+UseCompressedClassPointers -XX:+useCompressedOops
    // Oops ；ordinary object pointers
    private static class P {
        //8 _markword
        //4 _oop指针
        int id;         //4
        String name;    //4
        int age;        //4

        byte b1;        //1
        byte b2;        //1

        Object o;       //4
        byte b3;        //1

    }
}
