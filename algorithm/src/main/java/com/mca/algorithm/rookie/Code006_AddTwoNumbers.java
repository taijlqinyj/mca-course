package com.mca.algorithm.rookie;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ClassName: Code006_AddTwoNumbers
 * Package: com.mca.algorithm.rookie
 * Description: 两个链表相加
 * 1 -> 2 -> 3 ==> 321
 * 4 -> 5 -> 6 ==> 654
 * 相加结果：975 ==> 5 -> 7 -> 9
 *
 * @Author: yujie.qin
 * @Create: 2023/4/4 - 17:31
 * @version: v1.0
 */
// 测试链接：https://leetcode.com/problems/add-two-numbers/
public class Code006_AddTwoNumbers {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        int carry = 0;
        int curNum = 0;
        while (curS != null) {
            curNum = curL.val + curS.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return l;
    }

    // 求链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Queue<Integer> queue1 = new LinkedList<>();
        ListNode header1 = l1;
        queue1.add(header1.val);
        while (header1.next != null){
            queue1.add(header1.next.val);
        }
        Queue<Integer> queue2 = new LinkedList<>();
        ListNode header2 = l2;
        queue2.add(header2.val);
        while (header2.next != null){
            queue1.add(header2.next.val);
        }

        int carry = 0;
        int sum = 0;
        if (queue1.size() >= queue2.size()){
            while (!queue2.isEmpty()){
                int a = ((LinkedList<Integer>) queue1).pop() + ((LinkedList<Integer>) queue2).pop() + carry;
                l1.val = a % 10;
                carry = a / 10;

            }
            while (!queue1.isEmpty()){
                int a = ((LinkedList<Integer>) queue1).pop() + carry;
                sum = a % 10;
                carry = a / 10;
            }
        }else {
            if (queue1.isEmpty()){

            }else {
                sum = ((LinkedList<Integer>) queue1).pop() + ((LinkedList<Integer>) queue2).pop();

            }
        }
        return header1;
    }

    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        System.out.println(((LinkedList<Integer>) queue1).pop());
    }
}
