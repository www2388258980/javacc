package com.data.structure.test.Line;

import com.data.structure.line.SqQueue;

public class SqQueueTest {
    public static void main(String[] args) {
        SqQueue queue = new SqQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.toString());
        queue.deQueue();
        System.out.println(queue.toString());
        System.out.println(queue.getHead());
    }
}
