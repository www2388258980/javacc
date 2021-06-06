package com.yj.t_2020_08;

public class Test_08_06_01 {
    /**
     * 时间复杂度:
     * **** 时间复杂度：O(log(x))，x中大约有log10(x)位数字
     * 空间复杂度:
     * **** (1)
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // [2^31,2^31-1]
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        Test_08_06_01 test = new Test_08_06_01();
        System.out.println("123 reverse: " + test.reverse(1147483642));
        System.out.println("-123 reverse: " + test.reverse(-154543));
        System.out.println(1147483649);
        System.out.println(Math.pow(2, 31) - 1);
//        System.out.println(9463847411);
    }
}
