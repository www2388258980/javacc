package com.data.structure.test.Line;

import com.data.structure.line.LinkStack;

public class Test_07_27 {
    /*
     * 阶乘问题[采用非递归方式]
     * 如果n不为1,循环执行一下操作
     * *** 将n压入栈,然后n--;
     * 将fact变量赋值为1;
     * 如果栈不为空,循环执行以下操作:
     * *** 弹出栈顶元素top, fact *= top
     */
    public long fact_no_recursion(long n) {
        LinkStack<Long> stack = new LinkStack<>();
        while (n > 1) {
            stack.push(n--);
        }
        long fact = 1;
        while (!stack.isEmpty()) {
            fact *= stack.pop();
        }

        return fact;
    }

    public static void main(String[] args) {
        System.out.println("5! = " + new Test_07_27().fact_no_recursion(5));
    }
}
