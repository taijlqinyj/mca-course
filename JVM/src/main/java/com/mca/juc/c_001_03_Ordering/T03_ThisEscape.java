package com.mca.juc.c_001_03_Ordering;

/**
 * ClassName: T03_ThisEscape
 * Package: com.mca.juc.c_001_03_Ordering
 * Description: this�����ݳ���������ʼ�������±�������Ĭ��ֵ״̬
 * this��û��ʼ����ɣ��ͱ���ȥʹ��
 * ��Ҫ�ڹ��췽����new�߳�������
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
