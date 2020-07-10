package com.data.structure.test.Line;

import com.data.structure.line.MyLinkList;

public class MyDuLinkListTest {
    public static void main(String[] args) {
        MyLinkList<Integer> list = new MyLinkList<>();
        Integer[] arr = new Integer[]{1, 4, 6, 8, 12};
        list.initList(arr);
        list.traver();
    }
}
