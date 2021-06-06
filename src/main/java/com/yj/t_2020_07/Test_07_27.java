package com.yj.t_2020_07;

import java.util.ArrayList;
import java.util.List;

public class Test_07_27 {
    /**
     * @param s
     * @param numRows
     * @return
     * @author yj
     */
    public String convert(String s, int numRows) {
        char[][] arr = new char[numRows][s.length()];

        int col = 0;
        int index = 0;
        while (index < s.length()) {
            for (int row = 0; row < numRows; row++) {
                if (col % (numRows - 1) == 0) {
                    arr[row][col] = s.charAt(index++);

                } else {
                    int row2 = numRows - 1 - col % (numRows - 1);
                    arr[row2][col] = s.charAt(index++);
                    break;
                }
                if (index == s.length())
                    break;
            }
            col++;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != '\0') {
                    str.append(arr[i][j]);
                }
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
//        char[] arr = new char[10];
//        arr[1] = 'a';
//        arr[3] = 'b';
//        arr[6] = 'c';
//        arr[9] = 'd';
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != '\0') {
//                System.out.println(arr[i]);
//            }
//        }

        String str1 = new Test_07_27().convert("LEETCODEISHIRING", 4);
        String str2 = new Test_07_27().convert2("LEETCODEISHIRING", 4);
        String str3 = new Test_07_27().convert3("LEETCODEISHIRING", 4);
        System.out.println("str1: " + str1);
        System.out.println("str1: " + str2);
        System.out.println("str3: " + str3);
    }


    /**
     * 时间复杂度:
     * ****O(n)
     * 空间复杂度:
     * ****O(n)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }


    public String convert3(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                /*
                 * 每行的第一个都在第一列,其下标是1,2...,numRows-1;
                 */
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }


}
