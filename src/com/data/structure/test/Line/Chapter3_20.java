package com.data.structure.test.Line;

import com.data.structure.line.LinkStack;

public class Chapter3_20 {
    /**
     * 将10进制n转换为system进制
     * 初始化一个空栈s
     * 当十进制n非零时,循环执行以下操作
     * **把n与system求余得到的数压入栈s
     * **将n更新为n与system的商
     * 当栈s非空时,依次输出值
     *
     * @param n      10进制n
     * @param system 代表几进制
     */
    public static void conversion(int n, int system) {
        if (system <= 1) {
            System.out.println("输入的进制不合法");
        }
        LinkStack<Integer> stack = new LinkStack<>();
        while (n != 0) {
            stack.push(n % system);
            n /= system;
        }
        System.out.println(stack.toString());
    }

    public static void main(String[] args) {
        conversion(100, 8);
        conversion(8, 2);
    }
}
