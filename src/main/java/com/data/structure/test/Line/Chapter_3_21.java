package com.data.structure.test.Line;

import com.data.structure.line.LinkStack;

public class Chapter_3_21 {

    public static void main(String[] args) {
        System.out.println(new Chapter_3_21().matching("([([][])])"));
    }

    /**
     * 括号的匹配
     * <p>
     * 初始化一个空栈s
     * 设置一标记型标量flag,用来标记匹配结果以及控制循环及返回结果，true表示正确匹配,
     * false表示错误匹配，初始值true
     * 依次读入字符ch,如果没有扫描完毕或者flag为真,则循环执行一下操作
     * ** 若ch是左括号'['或者'(',则将其压入栈
     * ** 若ch是右括号')',则根据当前栈顶元素的值分情况: 若栈非空且栈顶元素是'(',则正确
     * ** 匹配,否则错误匹配,flag之为false;
     * ** 若ch是右括号']',则根据当前栈顶元素的值分情况: 若栈非空且栈顶元素是'[',则正确
     * ** 匹配,否则错误匹配,flag之为false;
     * 退出循环后,如果栈空且flag为true,则匹配成功,返回true,否则返回false
     * <p>
     * 时间复杂度:
     * **** O(n)
     * 空间复杂度:
     * ****近似O(n)
     *
     * @param s
     * @return
     */
    public boolean matching(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        boolean flag = true;
        LinkStack<Character> stack = new LinkStack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                }
                char top = stack.pop();
                if (top != '(') {
                    flag = false;
                    break;
                }
            } else if (ch == ']') {
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                }
                char top = stack.pop();
                if (top != '[') {
                    flag = false;
                    break;
                }
            } else {
                flag = false;
                break;
            }
        }
        return flag && stack.isEmpty();
    }
}
