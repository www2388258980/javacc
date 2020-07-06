package com.data.structure.line;

/**
 * 单链表
 */
public class MyLinkList<T> {

    private LNode node;

    public MyLinkList() {
        // 头节点;
        node = new LNode();
        node.next = null;
    }

    /**
     * 前插法
     *
     * @param arr
     */
    public void initList_head(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            LNode p = new LNode(arr[i]);
            p.next = this.node.next;
            this.node.next = p;
        }
    }

    /**
     * 尾插法
     *
     * @param arr
     */
    public void initList_tail(T[] arr) {
        // 尾指针
        LNode tail = this.node;
        for (int i = 0; i < arr.length; i++) {
            LNode p = new LNode(arr[i]);
            tail.next = p;
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

    /**
     * 插入一个节点
     *
     * @param arg
     */
    public void add(T arg) {
        LNode p = this.node;
        while (p.next != null) {
            p = p.next;
        }
        // 这里p就已经是最后一个节点了;
        LNode q = new LNode(arg);
        p.next = q;
        q.next = null;
    }

    /**
     * 遍历
     */
    public void traver() {
        LNode p = this.node.next;
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

    public class LNode {
        private T data;
        private LNode next;

        public LNode() {
        }

        public LNode(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "LNode{" +
                    "data=" + data +
                    '}';
        }
    }

    /**
     * @param index 根据下标index获取元素值
     * @return
     */
    public T get(int index) {
        System.out.println("get(int index) 被调用......");
        int i = 0;
        LNode p = this.node.next;
        if (p == null) {
            return null;
        }
        while (p != null) {
            if (index == i) {
                return p.data;
            }
            i++;
            p = p.next;
        }
        /*
         * index < 0 or index 大于链表元素
         */
        return null;
    }

    /**
     * 类型 T 最好重写equals方法，不然比较的就是两个地址是否相同;
     *
     * @param data 获取第一个与data相同的节点;
     * @return
     */
    public LNode get(T data) {
        System.out.println("get(T data) 被调用......");
        LNode p = this.node.next;
        while (p != null) {
            // data可能为null，所用用p.data.equals而不是data.equals
            if (p.data.equals(data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
}
