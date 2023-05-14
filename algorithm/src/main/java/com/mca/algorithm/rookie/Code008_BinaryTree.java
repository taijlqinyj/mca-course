package com.mca.algorithm.rookie;

import java.util.*;

/**
 * ClassName: Code008_BinaryTree
 * Package: com.mca.algorithm.rookie
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/4/11 - 8:30
 * @version: v1.0
 */
public class Code008_BinaryTree {

    public static class ListNode{
        public int value;
        public ListNode next;
    }

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode o1, ListNode o2) {
            return o1.value - o2.value;
        }
    }

    /**
     * 合并 K 个有序链表
     * 使用优先级队列
     * */
    public static ListNode mergeKLists(ListNode[] nodes){
        if (nodes == null){
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null){
                heap.add(nodes[i]);
            }
        }
        if (heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null){
            heap.add(pre.next);
        }
        while (!heap.isEmpty()){
            ListNode current = heap.poll();
            pre.next = current;
            pre = current;
            if (current.next != null){
                heap.add(current.next);
            }
        }
        return head;
    }

    /**
     * 二叉树先序遍历
     * */
    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 二叉树中序遍历
     * */
    public static void middle(Node head){
        if (head == null){
            return;
        }
        middle(head.left);
        System.out.println(head.value);
        middle(head.right);
    }

    /**
     * 二叉树后序遍历
     * */
    public static void pos(Node head){
        if (head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    /**
     * 判断两颗树结构是否相同
     * */
    public static boolean isSameTree(Node node1, Node node2){
        if (node1 == null && node2 == null){
            return true;
        }
        if (node1 == null ^ node2 == null){
            return false;
        }
        return node1.value == node2.value && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }

    /**
     * 一颗二叉树是否为对称的，镜面树
     * */
    public static  boolean isSymmetrical(Node pRoot) {
        return isMirror(pRoot, pRoot);
    }
    public static boolean isMirror(Node node1, Node node2){
        if (node1 == null && node2 == null){
            return true;
        }
        if (node1 == null ^ node2 == null){
            return false;
        }
        return node1.value == node2.value && isSameTree(node1.left, node2.right) && isSameTree(node1.right, node2.left);
    }

    /**
     * 返回一棵树的最大深度
     * */
    public static int maxDepth(Node node){
        if (node == null){
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * 给出一棵树的先序遍历结果和中序遍历结果
     * 重建这颗树
     * 时间复杂度：O(N)
     * */
    public static Node reConstructBinaryTree(int [] pre, int [] vin) {
        if (pre == null || vin == null || pre.length != vin.length){
            return null;
        }
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < vin.length; i++) {
            valueIndexMap.put(vin[i], i);
        }
        return f(pre, 0, pre.length - 1, vin, 0, vin.length - 1, valueIndexMap);
    }
    public static Node f(int[] pre, int L1, int R1, int[] vin, int L2, int R2, HashMap<Integer, Integer> valueIndexMap){
        // 左树为空或右树为空的情况
        if (L1 > R1){
            return null;
        }
        Node head = new Node(pre[L1]);
        if (L1 == R1){
            return head;
        }
        // 从中序遍历中找到根节点，用find表示
        /*int find = L2;
        while (vin[find] != pre[L1]){
            find++;
        }*/
        int find = valueIndexMap.get(pre[L1]);
        // 根据中序结果得出：根节点的左树范围是L2 -- find-1，右树范围是 find+1 -- R2
        // 则先序遍历中：根节点的左树范围是L1+1 -- L1+find-L2，右树范围是 L1+find-L2+1 -- R1
        head.left = f(pre, L1 + 1, L1 + find - L2, vin, L2, find - 1, valueIndexMap);
        head.right = f(pre, L1+find-L2+1, R1, vin, find + 1, R2, valueIndexMap);
        return head;
    }

    /**
     * 二叉树按层遍历并收集节点
     * 1、拿出此时队列的size
     * 2、弹出节点，先左，再右
     * 倒序就 ans.add(0, currentAns);
     * 正序就 ans.add(currentAns);
     * */
    public static ArrayList<ArrayList<Integer>> levelOrder(Node root){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> currentAns = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                currentAns.add(current.value);
                if (current.left != null){
                    queue.add(current.left);
                }
                if (current.right != null){
                    queue.add(current.right);
                }
            }
            ans.add(0, currentAns);
        }
        return ans;
    }

    /**
     * 判断一个树是平衡树
     * 每一颗子树都需要满足：|左树高度 - 右树高度| <= 1
     * */
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    public static boolean isBalanced(Node root){
        return process(root).isBalanced;
    }
    public static Info process(Node x){
        if (x == null){
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
                && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }

    /**
     * 判断一个树是搜索二叉树
     * 1、每一颗字数来说，左边的都比我小，右边的都比我大
     * 中序遍历结果严格有序，就是搜索二叉树
     * 递归实现
     * */
    public static class Info1 {
        public boolean isBST;
        public int max;
        public int min;

        public Info1(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
    public static boolean isBST(Node root){
        return process1(root).isBST;
    }
    public static Info1 process1(Node x){
        if (x == null){
            return null;
        }
        Info1 leftInfo = process1(x.left);
        Info1 rightInfo = process1(x.right);

        int max = x.value;
        int min = x.value;
        if (leftInfo != null){
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null){
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = true;
        // 左树不为空，且左树不是搜索二叉树
        if (leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }
        // 右树不为空，且右树不是搜索二叉树
        if (rightInfo != null && !rightInfo.isBST){
            isBST = false;
        }
        // 左树的最大值 < x.value
        boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < x.value;
        // 右树的最小值 > x.value
        boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > x.value;
        if (!leftMaxLessX || !rightMinMoreX){
            isBST = false;
        }
        return new Info1(isBST, max, min);
    }

    /**
     * 判断一颗树是不是完全二叉树
     * 除了叶子节点，每个节点都有两个子节点
     * */
    public boolean isAllTreeBST(Node root){
        if(root == null) return true;
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node left = null;
        Node right = null;
        boolean flag = false; //标记是否遇到节点不双全的节点
        while(!queue.isEmpty()){
            root = queue.poll();
            left = root.left;
            right = root.right;
            // 遇到左右孩子不双全的节点并且该节点不是叶子节点的时候就不是完全二叉树
            // 左孩子为空并且右孩子不为空的时候不是完全二叉树
            if((flag && !(left == null && right == null)) || (left == null && right != null)){
                return false;
            }
            if(left != null){
                queue.offer(left);
            }
            if(right != null){
                queue.offer(right);
            }
            if(left == null || right == null){
                flag = true;
            }
        }
        return true;
    }

    /**
     * 能否组成路径和
     * */
    public static boolean isSum = false;
    public static boolean hasPathSum(Node root, int sum){
        if (root == null){
            return false;
        }
        // return hasPathSum1(root, sum);
        isSum = false;
        hasPathSum2(root, 0, sum);
        return isSum;
    }
    public static boolean hasPathSum1(Node root, int sum){
        if (root.left == null && root.right == null){
            return root.value == sum;
        }
        boolean ans = root.left != null ? hasPathSum1(root.left, sum - root.value) : false;
        ans |= root.right != null ? hasPathSum1(root.right, sum - root.value) : false;
        return ans;
    }
    public static void hasPathSum2(Node root, int preSum, int sum){
        // root是叶子节点
        if (root.left == null && root.right == null){
            if (root.value + preSum == sum){
                isSum = true;
            }
            return;
        }
        // root是非叶子节点
        preSum += root.value;
        if (root.left != null){
            hasPathSum2(root.left, preSum, sum);
        }
        if (root.right != null){
            hasPathSum2(root.right, preSum, sum);
        }
    }

    /**
     * 收集所有路径和
     * */
    public static List<List<Integer>> pathSum(Node root, int sum){
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        List<Integer> path = new LinkedList<>();
        process(root, path, 0, sum, ans);
        return ans;
    }
    public static void process(Node root, List<Integer> path, int preSum, int sum, List<List<Integer>> ans){
        if (root.left == null && root.right == null){
            if (preSum + root.value == sum){
                path.add(root.value);
                ans.add(copy(path));
                // 这一步是因为还要回到父节点，让父节点去找其它兄弟节点
                // 清理现场
                path.remove(path.size() - 1);
            }
            return;
        }
        // root是非叶子节点
        path.add(root.value);
        preSum += root.value;
        if (root.left != null){
            process(root.left, path, preSum, sum, ans);
        }
        if (root.right != null){
            process(root.right, path, preSum, sum, ans);
        }
        path.remove(path.size() - 1);
    }
    public static List<Integer> copy(List<Integer> path){
        List<Integer> ans = new ArrayList<>();
        for (Integer integer : path){
            ans.add(integer);
        }
        return ans;
    }

    /**
     * 出现一次的数字
     * */
    public static int singleNumber (int[] A) {
        // 哈希表解法
//        int res = -1;
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < A.length; i++)
//            if (map.containsKey(A[i])) {
//                Integer integer = map.get(A[i]);
//                integer += 1;
//                map.put(A[i], integer);
//            } else {
//                map.put(A[i], 1);
//            }
//        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
//            if (integerIntegerEntry.getValue() == 1){
//                res =  integerIntegerEntry.getKey();
//            }
//        }
//        return res;

        // 位运算异或解法
        // 相同的数字异或结果为0
        int num = 0;
        for(int i=0; i < A.length; i++){
            num^=A[i];
        }
        return num;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,0,1};
        System.out.println(singleNumber(a));
    }
}
