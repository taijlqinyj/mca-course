package com.mca.collection.linked;

import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ClassName: Main
 * Package: com.mca.collection.linked
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/22 - 16:14
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        VirtualLinkedList<Integer> vll = new VirtualLinkedList<>();
        vll.add(1);
        vll.add(2);
        vll.add(3);
        System.out.println(vll.getSize());
        System.out.println(vll.get(0));
        System.out.println(vll.get(4));

    }
}
