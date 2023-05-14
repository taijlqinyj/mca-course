package com.mca.algorithm.rookie;

/**
 * ClassName: Code001_printBinary
 * Package: com.mca.algorithm.rookie
 * Description: 打印二进制
 *
 * @Author: yujie.qin
 * @Create: 2023/4/3 - 9:01
 * @version: v1.0
 */
public class Code001_printBinary {
    public static void main(String[] args) {
        printB(3);
        int a = 2;
        printB(a);
        printB(a << 1);
        printB(a << 2);
        printB(a << 4);
        printB(a >>> 1);
        printB(Integer.MIN_VALUE);
    }

    public static void printB(int num){
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
