package com.data.structure.test.Line;

import com.data.structure.line.LinkStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Chapter_3_22 {


    public static void main(String[] args) {
        int res = new Chapter_3_22().evalueateExpression();
        System.out.println("表达式结算结果: " + res);
    }

    /**
     * 简单表达式求值
     * <p>
     * 初始化optr栈(运算符栈)和opnd栈(操作数栈),将表达式起始符"#"压入optr栈.
     * 扫描表达式,读入第一个字符串str,如果表达式没有扫描完毕或者optr栈顶元素不为"#"是,循环执行以下操作:
     * ** 若ch不是运算符,则压入opnd栈,读入下一个字符串;
     * ** 若ch是运算符,则根据optr的栈顶元素和str的优先级比较结果,做一下处理:
     * **** 若是小于,则str压入optr栈,读入下一个字符串str;
     * **** 若是大于,则弹出optr栈顶运算符,从opnd弹出两个数,进行运算,结果压入opnd栈;
     * **** 若是等于,则optr的栈顶元素是"("且str是")",这是弹出optr的栈顶元素,相等于括号匹配成功,然后继续读入下一个字符串str
     * opnd栈顶元素即使表达式求值的结果.
     *
     * @return
     */
    public int evalueateExpression() {
        Scanner scanner = new Scanner(System.in);
        LinkStack<String> optr = new LinkStack<>();
        optr.push("#");
        LinkStack<String> opnd = new LinkStack<>();
        System.out.print("输入: ");
        String str = scanner.next();
        while (!str.equals("#") || !optr.getTop().equals("#")) {
            if (!this.in(str)) {
                opnd.push(str);
                System.out.print("输入: ");
                str = scanner.next();
            } else {
//                System.out.println("优先级关系: " + this.precede(optr.getTop(), str));
                if (this.precede(optr.getTop(), str) == 3) {
//                    System.out.println("3");
                    optr.push(str);
                    System.out.print("输入: ");
                    str = scanner.next();
                } else if (this.precede(optr.getTop(), str) == 4) {
//                    System.out.println("4");
                    int num2 = Integer.parseInt(opnd.pop());
                    int num1 = Integer.parseInt(opnd.pop());
                    opnd.push(this.operate(num1, num2, optr.pop()) + "");
                } else {
//                    System.out.println("2");
                    if (optr.getTop().equals("(") && ")".equals(str)) {
                        optr.pop();
                        System.out.print("输入: ");
                        str = scanner.next();
                    } else {
//                        System.out.println("str: " + str + "top: " + optr.getTop());
                        throw new RuntimeException("括号不匹配!");
                    }
                }
            }
        }
        return Integer.parseInt(opnd.getTop());
    }

    /*
     * 判断str是否是字符串
     */
    private boolean in(String str) {
        if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str) || "(".equals(str) ||
                ")".equals(str) || "#".equals(str)) {
            return true;
        }
        return false;
    }


    private int precede(String str1, String str2) {
        /*
         * 4 str1优先级高;
         * 3 str2优先级高;
         * 2 两者优先级相等;
         * 1 出现错误;
         */
        int[][] prior = new int[][]{
                {4, 4, 3, 3, 3, 4, 4},
                {4, 4, 3, 3, 3, 4, 4},
                {4, 4, 4, 4, 3, 4, 4},
                {4, 4, 4, 4, 3, 4, 4},
                {3, 3, 3, 3, 3, 2, 1},
                {4, 4, 4, 4, 1, 4, 4},
                {3, 3, 3, 3, 3, 1, 2},
        };
        Map<String, Integer> map = new HashMap<>();
        map.put("+", 0);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 3);
        map.put("(", 4);
        map.put(")", 5);
        map.put("#", 6);
        Integer index1 = map.get(str1);
        Integer index2 = map.get(str2);
        // 默认map包括传入的所以运算符
        return prior[index1][index2];
    }

    private static final int NAN = 0xffffffff;


    private int operate(int num1, int num2, String optr) {
        int result = NAN;
        switch (optr) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }
}
