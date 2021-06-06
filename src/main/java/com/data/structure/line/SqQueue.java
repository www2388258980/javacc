package com.data.structure.line;

/**
 * 循环顺序队列
 * 少用一个元素空间，当队列空间大小为m时,有m-1个元素即认为队满
 * 当队头指针和队尾指针相同时，则任务队空
 */
public class SqQueue {
    private int[] base;
    private int front; // 队头
    private int rear; // 队尾
    private int length;

    public static final int DEFAULTSIZE = 16;

    public static final int NAN = 0XEFFFFFFF;

    public SqQueue() {
        this(DEFAULTSIZE);
    }

    public SqQueue(int length) {
        this.base = new int[length];
        this.front = 0;
        this.rear = 0;
        this.length = length;
    }

    public int length() {
        return this.length;
    }

    public void enQueue(int num) {
        if ((this.rear + 1) % this.length == this.front) {
            return;
        }
        this.base[this.rear] = num;
        this.rear = (this.rear + 1) % this.length;
    }

    public int deQueue() {
        if (this.rear == this.front) {
            return NAN;
        }
        int res = this.base[this.front];
        this.front = (this.front + 1) % this.length;
        return res;
    }

    public int getHead() {
        if (this.rear == this.front) {
            return NAN;
        }
        return this.base[this.front];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        if (this.front == this.rear) {

        } else {
            int fro = this.front;
            while (fro != this.rear) {
                str.append(this.base[fro]);
                fro = (fro + 1) % this.length;
                if (fro != this.rear) {
                    str.append(",");
                }
            }
        }
        str.append("]");

        return str.toString();
    }
}
