package com.data.structure.line;

/**
 * 单链表
 */
public class MyLinkList<T> {

    private LNode<T> node;

    public MyLinkList() {
        // 头节点;
        node = new LNode<T>();
        node.next = null;
    }

    /**
     * 前插法
     *
     * @param arr
     */
    public void initList_head(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            LNode<T> p = new LNode<T>(arr[i]);
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
        LNode<T> tail = this.node;
        for (int i = 0; i < arr.length; i++) {
            LNode<T> p = new LNode<T>(arr[i]);
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
        LNode<T> p = this.node;
        while (p.next != null) {
            p = p.next;
        }
        // 这里p就已经是最后一个节点了;
        LNode<T> q = new LNode<T>(arg);
        p.next = q;
        q.next = null;
    }

    /**
     * 遍历
     */
    public void traver() {
        LNode<T> p = this.node.next;
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
     * @param index 根据下标index获取元素值
     * @return
     */
    public T get(int index) {
        System.out.println("get(int index) 被调用......");
        int i = 0;
        LNode<T> p = this.node.next;
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
    public LNode<T> get(T data) {
        System.out.println("get(T data) 被调用......");
        LNode<T> p = this.node.next;
        while (p != null) {
            // data可能为null，所用用p.data.equals而不是data.equals
            if (p.data.equals(data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * @param index 根据下标index移除元素
     */
    public LNode<T> remove(int index) {
        int i = 0;
        LNode<T> q = this.node;
        LNode<T> p = this.node.next;
        while (p != null) {
            if (i == index) {
                q.next = p.next;
                return p;
            }
            i++;
            q = p;
            p = p.next;
        }
        // index < 0 or index >= 元素最大长度
        return null;
    }

    /**
     * @param target 节点数据与和target比较，相等则删除该元素
     * @return
     */
    public LNode<T> remove(T target) {
        LNode<T> q = this.node;
        LNode<T> p = this.node.next;
        while (p != null) {
            if (p.data.equals(target)) {
                q.next = p.next;
                return p;
            }
            q = p;
            p = p.next;
        }
        return null;
    }

    public LNode<T> getNode() {
        return node;
    }

    public void setNode(LNode<T> node) {
        this.node = node;
    }
}
