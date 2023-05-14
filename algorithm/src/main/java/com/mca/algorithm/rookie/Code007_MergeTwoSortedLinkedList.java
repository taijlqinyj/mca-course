package com.mca.algorithm.rookie;

/**
 * ClassName: Code007_MergeTwoSortedLinkedList
 * Package: com.mca.algorithm.rookie
 * Description: 合并两个有序链表
 * 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
 * 1 -> 3 -> 5-> 7 -> 9 -> 10
 * 2 -> 4 -> 6 -> 8
 * 结果：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
 *
 * @Author: yujie.qin
 * @Create: 2023/4/4 - 17:33
 * @version: v1.0
 */
public class Code007_MergeTwoSortedLinkedList {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}
