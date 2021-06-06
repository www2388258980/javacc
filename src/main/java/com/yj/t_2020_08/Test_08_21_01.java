package com.yj.t_2020_08;

public class Test_08_21_01 {
    public static void main(String[] args) {
        Test_08_21_01 test = new Test_08_21_01();
        System.out.println("1234567" + (test.isHuiwenshu(1234567) ? "是回文数!" : "不是回文数!"));
        System.out.println("+1234321" + (test.isHuiwenshu(+1234321) ? "是回文数!" : "不是回文数!"));
        System.out.println("-1234321" + (test.isHuiwenshu(-1234321) ? "是回文数!" : "不是回文数!"));
    }

    /**
     * <p>
     * 时间复杂度:
     * **** O(logn) -- 以10为底,n(数字)为对数
     * 空间复杂度:
     * **** O(1)
     *
     * @param num
     * @return
     */
    public boolean isHuiwenshu(int num) {
        // 特殊情况：
        // 如上所述，当 num < 0 时，num 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }
        int sum = 0;
        /*
         * 反转一半数字
         * 比如:
         * (1) 偶数位数 123321  num: 123 sum: 123
         * (2) 奇数位数 1234321 num: 123 sum: 1234
         */
        while (num > sum) {
            sum = sum * 10 + num % 10;
            num /= 10;
        }
        return sum == num || sum / 10 == num;
    }
}
