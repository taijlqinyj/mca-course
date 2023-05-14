package com.mca.algorithm.rookie;

/**
 * ClassName: Code005_ReverseNodesInKGruop
 * Package: com.mca.algorithm.rookie
 * Description: K 个节点的组内逆序调整
 *
 * @Author: yujie.qin
 * @Create: 2023/4/4 - 13:37
 * @version: v1.0
 */
public class Code005_ReverseNodesInKGruop {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组凑齐了！
        head = end;
        reverse(start, end);
        // 上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    /**
     * 传入开始的节点，和间隔数K，返回间隔K-1个节点后的节点信息
     * 边界问题考虑
     * */
    public static ListNode getKGroupEnd(ListNode start, int k){
        while (--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    public static void main(String[] args) {

    }

}
