package com.mca.algorithm.rookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName: Code004_DoubleLinkedList2Queue
 * Package: com.mca.algorithm.rookie
 * Description: 双向链表转队列
 *
 * @Author: yujie.qin
 * @Create: 2023/4/4 - 10:42
 * @version: v1.0
 */
public class Code004_DoubleLinkedList2Queue {

    class Node<V> {
        Node<V> next;
        Node<V> last;
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public class MyDqeue<V> {
        Node<V> head;
        Node<V> tail;
        int size;


        public MyDqeue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        /**
         * 头插法
         * */
        public void pushHead(V item){
            Node<V> node = new Node<V>(item);
            if (head == null){
                head = node;
                tail = node;
            }else {
                // 从头插
                node.next = head;
                head.last = node;
                head = node;
            }
            size++;
        }

        /**
         * 尾插法
         * */
        public void pushTail(V item){
            Node<V> node = new Node<V>(item);
            if (head == null){
                head = node;
                tail = node;
            }else {
                // 从尾插
                tail.next = node;
                node.last = tail;
                tail = node;
            }
            size++;
        }

        /**
         * 从头弹出
         * */
        public V popHead(V item){
            V ans = null;
            if (head == null){
                return ans;
            }
            size--;
            ans = head.value;
            if (head == tail){
                head = null;
                tail = null;
            } else {
                head = head.next;
                // 防止内存泄露
                head.last = null;
            }
            return ans;
        }

        /**
         * 从尾弹出
         * */
        public V popTail(V item){
            V ans = null;
            if (tail == null){
                return ans;
            }
            size--;
            ans = tail.value;
            if (tail == head){
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                // 防止内存泄露
                tail.next = null;
            }
            return ans;
        }

        /**
         * 从头弹出，不踢出元素
         * */
        public V peekHead(V item){
            V ans = null;
            if (head == null){
                return ans;
            }
            ans = head.value;
            return ans;
        }

        /**
         * 从尾弹出，不踢出元素
         * */
        public V peekTail(V item){
            V ans = null;
            if (tail == null){
                return ans;
            }
            ans = tail.value;
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  n  = sc.nextInt();

        String s = " " + sc.next();

        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++)
            sum[i] = sum[i - 1] + s.charAt(i) - '0';

        int res = n;
        for (int i = 0; i <= n; i++) {
            res = Math.min(res, i - sum[i] + sum[n] - sum[i]);
        }
        System.out.println(res);
    }
}
