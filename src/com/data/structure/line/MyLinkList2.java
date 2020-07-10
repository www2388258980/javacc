package com.data.structure.line;

import java.io.Serializable;

/*
 * 单链表
 */
public class MyLinkList2<T> implements Serializable {

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

        public Node<T> next() {
            if (this.next == null) {
                return null;
            }
            return this.next;
        }

        public T data() {
            return this.data;
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
            p = p.next();
            if (p != null) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }


}
