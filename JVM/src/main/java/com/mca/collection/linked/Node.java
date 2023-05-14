package com.mca.collection.linked;

/**
 * ClassName: Node
 * Package: com.mca.collection.linked
 * Description: LinkedList Node节点对象
 *
 * @Author: yujie.qin
 * @Create: 2023/3/22 - 15:39
 * @version: v1.0
 */
public class Node<E> {
    // 当前节点上一个节点
    private Node<E> pre;

    // 当前节点的元素值
    private E value;

    // 当前节点的下一个节点
    private Node<E> next;

    public Node<E> getPre() {
        return pre;
    }

    public void setPre(Node<E> pre) {
        this.pre = pre;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
