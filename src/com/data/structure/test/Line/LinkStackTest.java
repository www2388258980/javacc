package com.data.structure.test.Line;

import com.data.structure.line.LinkStack;

public class LinkStackTest {
    public static void main(String[] args) {
        LinkStack<String> stack = new LinkStack<>();
        stack.push("yangji1");
        stack.push("yangji2");
        stack.push("yangji3");
        stack.push("yangji4");
        stack.toString();
        stack.pop();
        stack.toString();
    }
}
