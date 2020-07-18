package com.data.structure.line;

/**
 * 链栈
 * 栈空时: top == null
 * 栈为空时: top为栈顶元素
 *
 * @param <T>
 */
public class LinkStack<T> {

    private StackNode<T> top;

    public LinkStack() {

    }


    private static class StackNode<T> {
        private T data;
        private StackNode next;

        public StackNode(T data) {
            this(data, null);

        }

        public StackNode(T data, StackNode<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public void push(T arg1) {
        StackNode<T> p = new StackNode<>(arg1, this.top);
        this.top = p;
    }

    public T pop() {
        if (this.top == null) {
            return null;
        }
        StackNode<T> p = this.top;
        this.top = this.top.next;
        return p.data;
    }

    public T getTop() {
        return this.top == null ? null : this.top.data;
    }

    @Override
    public String toString() {
        System.out.print("[");
        StackNode<T> p = this.top;
        while (p != null) {
            System.out.print(p.data.toString());
            if (p.next != null) {
                System.out.print(",");
            }
            p = p.next;
        }
        System.out.println("]");
        return super.toString();
    }
}
