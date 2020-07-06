package com.data.structure.test;

import com.data.structure.line.MyLinkList;

public class MyLinkListTest {
    public static void main(String[] args) {
        MyLinkList<Integer> list = new MyLinkList<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        list.initList(arr);
        list.traver();
        Integer[] arr2 = new Integer[]{5, 4, 3, 2, 1};
        MyLinkList<Integer> list2 = new MyLinkList<>();
        list2.initList_head(arr2);
        list2.traver();

        System.out.println("add test................................");
        list.add(6);
        list.traver();
        System.out.println("get(int index) test.....................");
        System.out.println(list.get(5));
        System.out.println(list2.get(-1));
        System.out.println("get(T data) test........................");
        System.out.println(list.get(3));
        System.out.println(list.get(new Integer(3)));
    }
}
