package com.mca.jvm.runtimeDataAreaAndInstructionSet;

/**
 * ClassName: Hello_02
 * Package: com.mca.jvm.runtimeDataAreaAndInstructionSet
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/13 - 23:50
 * @version: v1.0
 */
public class Hello_02 {
    public static void main(String[] args) {
        Hello_02 h = new Hello_02();
        h.m1();
    }

    public void m1() {
        int i = 200;
    }

    public void m2(int k) {
        int i = 300;
    }

    public void add(int a, int b) {
        int c = a + b;
    }

    public void m3() {
        Object o = null;
    }

    public void m4() {
        Object o = new Object();
    }
}
