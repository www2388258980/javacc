package com.data.structure.line;

/**
 * 链队
 * 增加头节点,当队空时，头节点.next为null
 * 当队不空时,队尾指针始终指向尾节点
 *
 * @param <T>
 */
public class LinkQueue<T> {

    private QueueNode<T> front;
    private QueueNode<T> rear;

    private static class QueueNode<Type> {
        private Type data;
        private QueueNode<Type> next;

        public QueueNode() {

        }

        public QueueNode(Type data) {
            this.data = data;
        }
    }

    public LinkQueue() {
        this.front = new QueueNode<>();
        this.rear = front;
    }

    public void enQueue(T arg1) {
        QueueNode<T> node = new QueueNode<>(arg1);
        this.rear.next = node;
        this.rear = node;
    }

    public T deQueue() {
        if (this.front == this.rear) {
            return null;
        }
        QueueNode<T> p = this.front.next;
        if (p == this.rear) {
            this.rear = this.front;
        } else {
            this.front.next = p.next;
        }

        return p.data;
    }

    public T getHead() {
        if (this.front == this.rear) {
            return null;
        }
        return this.front.next.data;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        QueueNode<T> p = this.front.next;
        while (p != null) {
            str.append(p.data.toString());
            if (p.next != null) {
                str.append(",");
            }
            p = p.next;
        }
        str.append("]");
        return str.toString();
    }
}
