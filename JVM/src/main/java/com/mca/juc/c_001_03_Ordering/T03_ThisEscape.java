package com.mca.juc.c_001_03_Ordering;

/**
 * ClassName: T03_ThisEscape
 * Package: com.mca.juc.c_001_03_Ordering
 * Description: this对象逸出，对象半初始化，导致变量处于默认值状态
 * this还没初始化完成，就被拿去使用
 * 不要在构造方法里new线程且启动
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 9:14
 * @version: v1.0
 */
public class T03_ThisEscape {

    private int num = 8;


    public T03_ThisEscape() {
        new Thread(() -> System.out.println(this.num)
        ).start();
    }

    public static void main(String[] args) throws Exception {
        new T03_ThisEscape();
        System.in.read();
    }
}
