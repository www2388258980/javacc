package com.yj.t_2020_09;

public class Test_01 {
    public static void main(String[] args) {
        Object[] arr = new Object[]{new Object(), new String("111"), new Integer(1),
                new Character('c')};
        Object[] arr2 = arr;
        arr2[1] = new String("222222222");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i].toString());
        }
    }
}
