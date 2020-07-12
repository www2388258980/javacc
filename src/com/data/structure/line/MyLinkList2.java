package com.data.structure.line;

import java.io.Serializable;

/*
 * 单链表
 */
public class MyLinkList2<T extends Comparable> implements Serializable {

    // 头节点;
    private Node<T> headNode;

    public MyLinkList2() {
        headNode = new Node<>(null, null);
    }


    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    /**
     * 前插法
     *
     * @param arr
     */
    public void initList_head(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Node<T> p = new Node<>(arr[i], this.headNode.next);
            this.headNode.setNext(p);
        }
    }

    /**
     * 尾插法
     *
     * @param arr
     */
    public void initList_tail(T[] arr) {
        // 尾指针
        Node<T> tail = this.headNode;
        for (int i = 0; i < arr.length; i++) {
            Node<T> p = new Node<>(arr[i], null);
            tail.setNext(p);
            tail = p;
        }
    }

    /**
     * 默认使用尾插法
     *
     * @param arr
     */
    public void initList(T[] arr) {
        initList_tail(arr);
    }

    public void traver() {
        Node<T> p = this.headNode.next;
        System.out.print("[");
        while (p != null) {
            System.out.print(p.data);
            p = p.next;
            if (p != null) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    /**
     * 删除递增有序链表中值大于mink且小于maxk的所有元素
     * <p>
     * 思路：
     * ***遍历递增链表,找到最后一个元素中的值小于等于mink，将下一个元素赋给p，找到最后一个元素中的值小于maxk，将其赋给q
     * ***那么p和q([p,q])中间的元素节点就满足条件
     * 时间复杂度:
     * ***O(n)  --- n为链表的长度
     * 空间复杂度
     * ***O(1)
     *
     * @param mink
     * @param maxk
     */
    public void removeSection(T mink, T maxk) {
        if (mink.compareTo(maxk) >= 0) {
            return;
        }
        Node<T> t = this.headNode.next;
        Node<T> p = null;
        Node<T> q = null;
        while (t != null) {
            T data = t.data;
            if (data.compareTo(mink) <= 0) {
                p = t.next;
            }
            if (data.compareTo(maxk) < 0) {
                q = t;
            }
            t = t.next;

        }

        if (p == null) { // 代表链表中的元素都大于mink
            q.next = null;
        } else {
            this.headNode.next = p;
            q.next = null;
        }
    }


}
