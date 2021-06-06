package com.data.structure.test.Line;


import com.data.structure.line.MyLinkList2;

public class MyLinkList2Test {
    public static void main(String[] args) {
        MyLinkList2<Integer> list = new MyLinkList2<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        list.initList(arr);
        list.traver();
    }
}
