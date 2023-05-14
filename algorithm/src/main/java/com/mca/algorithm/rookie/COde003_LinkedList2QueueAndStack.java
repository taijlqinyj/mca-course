package com.mca.algorithm.rookie;

/**
 * ClassName: COde003_LinkedList2QueueAndStack
 * Package: com.mca.algorithm.rookie
 * Description: 单向链表转队列
 *
 * @Author: yujie.qin
 * @Create: 2023/4/4 - 9:04
 * @version: v1.0
 */
public class COde003_LinkedList2QueueAndStack {

    static class Node<V> {
        V value;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int getSize(){
            return size;
        }

        public void offer(V value){
            Node<V> current = new Node<V>(value);
            if (tail == null){
                head = current;
                tail = current;
            }else {
                tail.next = current;
                tail = current;
            }
            size++;
        }

        public V poll(){
            V node = null;
            if (head != null){
                node = head.value;
                head = head.next;
                size--;
            }
            if (head == null){
                tail = null;
            }
            return node;
        }

        public V peek(){
            V node = null;
            if (head != null){
                node = head.value;
            }
            return node;
        }
    }

    public static class MyStack<V> {
        int size;
        Node<V> head;

        public MyStack() {
            head = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean empty() {
            return size == 0;
        }

        public V push(V item){
            Node<V> node = new Node<V>(item);
            if (head == null){
                head = node;
                size++;
            }else {
                node.next = head;
                head = node;
                size++;
            }
            return item;
        }

        public V pop(){
            V item = null;
            if (head != null){
                item = head.value;
                head = head.next;
                size--;
            }
            return item;
        }

        public V peek(){
            return head != null ? head.value : null;
        }
    }

    public static void main(String[] args) {
//        MyQueue<Integer> queue = new MyQueue<Integer>();
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        System.out.println(queue.size);
//        System.out.println(queue.peek());
//        System.out.println(queue.size);
//        System.out.println(queue.poll());
//        System.out.println(queue.size);


    }
}
