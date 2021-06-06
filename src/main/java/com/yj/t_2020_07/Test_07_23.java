package com.yj.t_2020_07;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test_07_23 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("C:\\Users\\杨吉\\Desktop\\上海第一季度\\000052310000-ACCOUNT_SORT-2020-03-31.txt"));
            String str = null;
            while ((str = br.readLine()) != null) {
                String[] split = str.split("\001"); // SOH
                for (int i = 0; i < split.length; i++) {
                    System.out.println(split[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
