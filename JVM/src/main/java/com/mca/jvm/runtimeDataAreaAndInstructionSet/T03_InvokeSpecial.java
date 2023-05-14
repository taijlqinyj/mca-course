package com.mca.jvm.runtimeDataAreaAndInstructionSet;

/**
 * ClassName: T03_InvokeSpecial
 * Package: com.mca.jvm.runtimeDataAreaAndInstructionSet
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/13 - 23:54
 * @version: v1.0
 */
public class T03_InvokeSpecial {
    public static void main(String[] args) {
        T03_InvokeSpecial t = new T03_InvokeSpecial();
        t.m();
        t.n();
    }

    public final void m() {}
    private void n() {}
}
