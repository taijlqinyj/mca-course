package com.mca.jvm.gc;

/**
 * ClassName: StackOverFlow
 * Package: com.mca.jvm.gc
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/15 - 15:20
 * @version: v1.0
 */
public class StackOverFlow {

    public static void main(String[] args) {
        me();
    }

    static void me(){
        me();
    }
}
