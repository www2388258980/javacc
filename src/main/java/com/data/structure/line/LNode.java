package com.data.structure.line;

public class LNode<T> {
    public T data;
    public LNode next;

    public LNode() {
    }

    public LNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LNode getNext() {
        return next;
    }

    public void setNext(LNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LNode{" +
                "data=" + data +
                '}';
    }
}