package com.data.structure.line;

/**
 * 顺序栈
 */
public class SqStack {
    private int[] base;
    private int top;

    public static final int defaultSize;

    public static final int NAN = 0XEFFFFFFF;

    static {
        defaultSize = 16;
    }

    public SqStack() {
        /*
         * 栈空时,top == 0
         * 栈满时,top == base.length
         * 栈非空时,top始终指向栈顶的上一个元素
         */
        base = new int[defaultSize];
        top = 0;
    }

    /**
     * 入栈
     *
     * @param num
     */
    public void push(int num) {
        if (this.top == base.length) {
            return;
        }
        base[top++] = num;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (this.top == 0) {
            return SqStack.NAN;
        }
        return this.base[--top];
    }
}
