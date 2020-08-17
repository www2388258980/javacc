package com.yj.t_2020_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_08_12_02 {
    public static void main(String[] args) {
        Test_08_12_02 test = new Test_08_12_02();
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(test.myAtoi("       1234567892123"));
        System.out.println(test.myAtoi("4193 with words"));
        System.out.println(test.myAtoi("words and 987"));
        System.out.println(test.myAtoi2("       1234567892123"));
        System.out.println(test.myAtoi2("4193 with words"));
        System.out.println(test.myAtoi2("words and 987"));
    }

    /*
     请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

    如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
    假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
    该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：
    本题中的空白字符只包括空格字符 ' ' 。
    假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     */

    /**
     * [-2147483648,2,147483647]
     * <p>
     * 时间复杂度:
     * **** O(n),n为字符串的有效长度;
     * 空间复杂度:
     * **** O(1)
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || "".equals(str.trim())) {
            return 0;
        }
        str = str.trim();
        // 符号位,默认为正数
        int signBit = 1;
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '+':
                case '-':
                    if (i != 0) {
                        return sum * signBit;
                    }
                    signBit = '+' == ch ? signBit : -signBit;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    // 0在ASCII中码值对应的十进制是48
                    int m = ch - 48;

                    if (signBit == 1 && sum > Integer.MAX_VALUE / 10) {
                        return Integer.MAX_VALUE;
                    } else if (signBit == 1 && sum == Integer.MAX_VALUE / 10 && m > 7) {
                        return Integer.MAX_VALUE;
                    } else if (signBit == -1 && sum > Integer.MAX_VALUE / 10) {
                        return Integer.MIN_VALUE;
                    } else if (signBit == -1 && sum == Integer.MAX_VALUE / 10 && m > 8) {
                        return Integer.MIN_VALUE;
                    }
                    sum = sum * 10 + m;
                    break;
                default:
                    if (i == 0) {
                        return 0;
                    }
                    return sum * signBit;
            }

        }
        return sum * signBit;
    }


    private int getCol(char c) {
        switch (c) {
            case ' ':
                return 0;
            case '+':
            case '-':
                return 1;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return 2;
        }
        return 3;
    }

    /**
     * <p>s
     *
     * @param str
     * @return
     * @author leetcode
     */
    public int myAtoi2(String str) {
        int sign = 1;
        int ans = 0;
        String state = "start";
        for (int i = 0; i < str.length(); i++) {
            int m = str.charAt(i) - 48;
            state = map.get(state).get(getCol(str.charAt(i)));
            if ("in_number".equals(state)) {
                if (sign == 1 && ans > Integer.MAX_VALUE / 10) {
                    return Integer.MAX_VALUE;
                } else if (sign == 1 && ans == Integer.MAX_VALUE / 10 && m > 7) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && ans > Integer.MAX_VALUE / 10) {
                    return Integer.MIN_VALUE;
                } else if (sign == -1 && ans == Integer.MAX_VALUE / 10 && m > 8) {
                    return Integer.MIN_VALUE;
                }
                ans = ans * 10 + m;
            } else if ("signed".equals(state)) {
                sign = str.charAt(i) == '+' ? 1 : 0;
            }
        }
        return sign * ans;
    }


    Map<String, List<String>> map = new HashMap<>();

    {
        List<String> list1 = new ArrayList<>();
        list1.add("start");
        list1.add("signed");
        list1.add("in_number");
        list1.add("end");
        map.put("start", list1);

        List<String> list2 = new ArrayList<>();
        list2.add("end");
        list2.add("end");
        list2.add("in_number");
        list2.add("end");
        map.put("signed", list2);

        List<String> list3 = new ArrayList<>();
        list3.add("end");
        list3.add("end");
        list3.add("in_number");
        list3.add("end");
        map.put("in_number", list3);

        List<String> list4 = new ArrayList<>();
        list4.add("end");
        list4.add("end");
        list4.add("end");
        list4.add("end");
        map.put("end", list4);
    }
}
