package com.yj;

import java.util.ArrayList;

import java.util.List;

/**
 * 校招研发编程测试题-3
 * 给定n个整数（0-100），其中0可以替换成任意其他数字，要求判断这n个整数是否连续？若不是连续数组，
 * 返回False，并返回或打印最长的连续子数组
 * <p>
 * 例：
 * 输入：0，5，6，7，8，10
 * 解释：将0替换成9，这n个整数可判定为连续
 * 输出：True
 * 输入：5，6，7，9，8，10
 * 解释：顺序无关
 * 输出：True
 * <p>
 * 输入：5，6，7，8，10
 * 输出：False，[5,6,7,8]
 * 输入：0，5，6，7，8，10，11，15
 * 输出：False，[5,6,7,8,0,10,11]
 */
public class Test2 {
    public static void main(String[] args) {
        /*
         * 对连续的理解: 相邻两个数满足升序or降序，如果一个数为0，必满足要求，并且0代表着一次补救机会;
         *
         * 首先将一个数组里面的0元素删除并对其计数;
         * 将相邻两个位置的元素拿出来比较x,y
         * 如果x,y不满足降序或者升序,则判断zeroCount>0，满足，减一，不满足，退出
         *
         * 进阶版本[未实现]：
         * 如果x，y不满降序或者升序，并且zeroCount == 0的情况下，不能退出，记录下当前连续子数组；然后继续从当前y元素开始判断;
         */
        int[] arr = new int[]{2, 0, 3, 8, 7, 6};
        int order = 0; // order代表降序或者升序; 1 降序,2升序; 其它什么也不代表;
        int zeroCount = 0; // 代表0的个数
        int flag = 1; // 1代表该数组有效; 0代表该数组无效;

        List<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                newArr.add(arr[i]);
            } else {
                zeroCount++;
            }
        }
        System.out.println(newArr);
        System.out.println(zeroCount);

        for (int i = 0; i < newArr.size() - 1; i++) {
            int x = newArr.get(i);
            int y = newArr.get(i + 1);
            if (order == 0) {
                if (x > y) {
                    order = 1;
                } else {
                    order = 2;
                }
                continue;
            } else {
                if ((x < y && order == 2) || (x > y && order == 1)) {

                } else {  // 进入这里代表相邻两个数不满足连续的要求;
                    if (zeroCount > 0) {
                        zeroCount--;
                    } else {
                        flag = 0;
                        break; // 退出
                    }
                }
            }
        }
        if (flag == 1) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }
}
