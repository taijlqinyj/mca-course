package com.mca.jvm.runtimeDataAreaAndInstructionSet;

/**
 * ClassName: Hello_04
 * Package: com.mca.jvm.runtimeDataAreaAndInstructionSet
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/13 - 23:50
 * @version: v1.0
 */
public class Hello_04 {
    public static void main(String[] args) {
        Hello_04 h = new Hello_04();
        int i = h.m(3);
    }

    public int m(int n) {
        if(n == 1) return 1;
        return n * m(n-1);
    }
}
