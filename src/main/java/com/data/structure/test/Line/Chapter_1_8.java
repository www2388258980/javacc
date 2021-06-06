package com.data.structure.test.Line;

import com.data.structure.line.MyLinkList2;

public class Chapter_1_8 {
    public static void main(String[] args) {
        MyLinkList2<Integer> list = new MyLinkList2<>();
        list.initList(new Integer[]{1, 3, 5, 7, 9});
        list.traver();
        list.removeSection(0, 11);
//        list.removeSection(3, 8);
//        list.removeSection(1, 9);

        list.traver();
    }


}
