package com.data.structure.line;

public class MyDuLinkList<T> {

    private static class DuLNode<T> {
        private T data;
        private DuLNode prior;
        private DuLNode next;

        public DuLNode() {

        }

        public DuLNode(T data) {
            this.data = data;
        }
    }

    private DuLNode node;

    public MyDuLinkList() {
        // 头节点;
        this.node = new DuLNode();
        this.node.prior = null;
    }


    /**
     * 前插法初始化双向链表
     *
     * @param arr
     */
    public void initList_head(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            DuLNode p = new DuLNode(arr[i]);
            p.next = this.node.next;
            p.prior = this.node;
            this.node.next = p;
        }
    }

    /**
     * 尾插法初始化双向链表
     *
     * @param arr
     */
    public void initList_tail(T[] arr) {
        DuLNode tail = this.node;
        for (int i = 0; i < arr.length; i++) {
            DuLNode p = new DuLNode(arr[i]);
            tail.next = p;
            p.prior = tail;
            tail = p;
        }
    }

    public void initList(T[] arr) {
        initList_tail(arr);
    }

    public void traver() {
        DuLNode p = this.node.next;
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

}
