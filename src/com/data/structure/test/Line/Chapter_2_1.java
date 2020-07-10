package com.data.structure.test.Line;

import com.data.structure.line.LNode;
import com.data.structure.line.MyLinkList;

/**
 * 将两个递增的有序链表合并为一个递增的有序链表.要求结果链表仍使用原来的
 * 两个链表的空间,不另外占用空间。表中不允许有重复的数据
 */
public class Chapter_2_1 {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        Integer[] arr2 = new Integer[]{2, 3, 6, 7, 12};
        MyLinkList<Integer> list1 = new MyLinkList<>();
        MyLinkList<Integer> list2 = new MyLinkList<>();
        list1.initList(arr1);
        list2.initList(arr2);
        MyLinkList<Integer> list3 = union(list1, list2);
        list3.traver();
    }

    /**
     * 将list1.next赋给p,list2.next赋给q
     * 当p不为空并且q也不为空的时候的,进行循环
     * [1]  if p和q的数据相等,则把p节点添加进新链表,然后p = p.next,q = q.next;
     * [2]  if p数据域小于q,则把p添加进新链表,并且更新 p = p.next;
     * [3]  否则把q添加进新链表,然后 q = q.next;
     * 跳出循环以后检测第二个链表还有未添加进去元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static MyLinkList<Integer> union(MyLinkList<Integer> list1, MyLinkList<Integer> list2) {
        LNode<Integer> tail = list1.getNode();
        LNode<Integer> p = list1.getNode().next;
        LNode<Integer> q = list2.getNode().next;
        while (p != null && q != null) {
            if (p.getData().equals(q.getData())) {
                tail.setNext(p);
                tail = p;
                p = p.getNext();
                q = q.getNext();
            } else if (p.getData() < q.getData()) {
                tail.setNext(p);
                tail = p;
                p = p.getNext();

            } else {
                tail.setNext(q);
                tail = q;
                q = q.getNext();

            }
        }
        while (q != null) {
            tail.setNext(q);
            tail = q;
            q = q.getNext();
        }
        return list1;
    }
}
