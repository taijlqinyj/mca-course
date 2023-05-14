package com.mca.collection.linked;

/**
 * ClassName: VirtualLinkedList
 * Package: com.mca.collection.linked
 * Description: 模拟 LinkedList 实现原理
 *
 * @Author: yujie.qin
 * @Create: 2023/3/22 - 16:14
 * @version: v1.0
 */
public class VirtualLinkedList<E> {

    // 头节点
    private Node<E> head;

    // 尾节点
    private Node<E> tail;

    // 长度
    private int count = 0;

    public VirtualLinkedList() {
    }

    // add element
    public void add(E val){
        Node<E> node = new Node<>();
        if (head == null){
            // first add
            node.setPre(null);
            node.setValue(val);
            node.setNext(null);
            // 头尾节点均是第一个元素
            head = node;
            tail = node;
        }else {
            // not first add
            Node<E> t = tail;
            node.setPre(t);
            node.setValue(val);
            node.setNext(null);
            // 尾插，操作尾指针
            t.setNext(node);
            tail = node;
        }
        count++;
    }

    public int getSize(){ return count; }

    public E get(int index){
        if (index > count){
            throw new RuntimeException("OutOfRange");
        }else {
            Node<E> n = head;
            for (int i = 0; i < index; i++) {
                n = head.getNext();
            }
            return n.getValue();
        }
    }
}
