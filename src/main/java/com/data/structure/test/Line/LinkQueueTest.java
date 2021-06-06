package com.data.structure.test.Line;

import com.data.structure.line.LinkQueue;

public class LinkQueueTest {
    public static void main(String[] args) {
        LinkQueue<String> linkQueue = new LinkQueue<>();
        String str1 = "str123";
        String str2 = "str123";
        String str3 = "str123";
        System.out.println("str1 == str2 : " + (str1 == str2));
        System.out.println("str1 == str3 : " + (str1 == str3));
        linkQueue.enQueue("str123");
        linkQueue.enQueue("str123");
        linkQueue.enQueue("str123");
        System.out.println(linkQueue.toString());
        linkQueue.deQueue();
        System.out.println(linkQueue.toString());
    }
}
