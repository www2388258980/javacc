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
        ListNode listNode2 = test.addTwoNumbers2(l1, l2);
        System.out.println(listNode2.getNum());

//        System.out.println(l1.getNum());
//        System.out.println(test.addTwoNumbers(l1, l2));
    }

    /**
     * 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1 和 l2 的表头开始相加。
     * 由于每位数字都应当处于 0…9的范围内，我们计算两个数字的和时可能会出现 “溢出”。
     * 例如，5+7=12。在这种情况下，我们会将当前位的数值设置为 2，
     * 并将进位 carry=1 带入下一次迭代。进位 carry必定是 0 或 1，
     * 这是因为两个数字相加（考虑到进位）可能出现的最大和为 9+9+1=19。
     * <p>
     * 伪代码如下：
     *
     * 将当前结点初始化为返回列表的哑结点。
     * 将进位 carry初始化为0。
     * 将 p和 q分别初始化为列表 l1 和 l2 的头部。
     * 遍历列表 l1 和 l2 直至到达它们的尾端。
     *    将 x设为结点 p的值。如果 p已经到达 l1的末尾，则将其值设置为 0。
     *    将 y设为结点 q的值。如果 q已经到达 l2的末尾，则将其值设置为 0。
     *    设定 sum=x+y+carry。
     *    更新进位的值，carry=sum/10。
     *    创建一个数值为 (sum mod 10)的新结点，
     *       并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     *    同时，将 p和 q前进到下一个结点。
     * 检查 carry=1是否成立，如果成立，则向返回列表追加一个含有数字 1的新结点。
     * 返回哑结点的下一个结点。
     * <p>
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     * <p>
     * 复杂度分析
     *    时间复杂度：O(max(m,n))，假设 m和 n分别表示 l1 和 l2的长度，上面的算法最多重复 max(m,n)次。
     *    空间复杂度：O(max(m,n))，新列表的长度最多为 max(m,n)+1。
     * <p>
     * @author leetcode
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode p = l1.next;
        ListNode q = l2.next;
        ListNode curr = l3;
        int carry = 0;
        while (p != null && q != null) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;
            int sum = x + y + carry;
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            curr = node;
            carry = sum / 10;
            p = p.next;
            q = q.next;
        }
        if(carry == 1) {
            ListNode node = new ListNode(1);
            curr.next = node;
        }

        return l3;
    }

}
