package com.yj.t_2020_08;

import java.io.UnsupportedEncodingException;

public class Test_04_01 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "s杨dfsdf过倚天屠龙dfjslf";
        String s1 = str.substring(0, 1);
        String s2 = str.substring(1, 2);
        String s3 = str.substring(0, 4);
        String s4 = str.substring(1, 9);
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);
        System.out.println("s4: " + s4);
        String str2 = "s杨dfsdf过倚天屠龙dfjslf".getBytes("ISO-8859-1").toString();
        System.out.println("str2:" + str2);
        String s5 = str2.substring(0, 1);
        String s6 = str2.substring(1, 4);
        String s7 = str2.substring(0, 4);
        String s8 = str2.substring(1, 9);
        System.out.println("s5: " + s5);
        System.out.println("s6: " + s6.getBytes("ISO-8859-1").toString());
        System.out.println("s7: " + s7.getBytes("ISO-8859-1").toString());
        System.out.println("s8: " + s8.getBytes("ISO-8859-1").toString());
    }
}
