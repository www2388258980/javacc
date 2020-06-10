package com.yj;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class Test4 {

    /**
     * 把链表中的存储的位数拿出来组装成数字;
     * 计算组装成的两个数的和;
     * 将结果拆分放进链表中
     *
     * 时间复杂度:
     *   O(n)
     * 空间复杂度:
     *  O(n)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = l1.getNum();
        int num2 = l2.getNum();
        int sum = num1 + num2;
        int x;

        List<Integer> list = new ArrayList<>();
        while (sum != 0) {
            x = sum % 10;
            list.add(x);
            sum /= 10;
        }
        ListNode node = new ListNode();
        for (int i = list.size() - 1; i >= 0; i--) {
            node.put(list.get(i));
        }
        return node;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int x) {
            val = x;
        }

        /**
         * 头插法
         *
         * @param num
         */
        public void put(int num) {
            ListNode node = new ListNode(num);
            if (this.next == null) {
                // 代表还没元素插进来,只有头节点;
                this.next = node;
                node.next = null;
            } else {
                node.next = this.next;
                this.next = node;
            }

        }


        /**
         * @return 返回当前链表存储的数
         */
        public int getNum() {
            ListNode t = this.next;
            int num = 0;
            int j = 0;
            while (t != null) {
                num += t.val * Math.pow(10, j);
                j++;
                t = t.next;
            }
            return num;
        }

    }

    public static void main(String[] args) {
        Test4 test = new Test4();
        ListNode l1 = test.new ListNode();
        l1.put(3);
        l1.put(4);
        l1.put(2);
        ListNode l2 = test.new ListNode();
        l2.put(4);
        l2.put(6);
        l2.put(5);
        ListNode listNode = test.addTwoNumbers(l1, l2);
        System.out.println(listNode.getNum());

//        System.out.println(l1.getNum());
//        System.out.println(test.addTwoNumbers(l1, l2));
    }


}
