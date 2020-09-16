package com.yj.t_2020_09;

/**
 * 动态规划-字符串解码
 */
public class Test_09_01 {
    public static int decode(String num) {
        int[] dp = new int[num.length()];
        dp[0] = 1; // 当nums只有1位时，只有1种解码方式
        for (int i = 0; i < num.length() - 1; i++) {
            int i1 = num.charAt(i) - 48;
            int i2 = num.charAt(i + 1) - 48;
            if (i1 >= 3 && i2 == 0) {
                dp[i + 1] = dp[i];
            } else if (i1 == 0 || (i1 == 2 && (i2 == 0 || i2 > 6)) ||
                    (i1 >= 3 && i2 > 0)) {
                dp[i + 1] = dp[i] + 1;
            } else if (i1 == 1 || (i1 == 2 && i2 > 0 && i2 <= 6)) {
                dp[i + 1] = dp[i] + 2;
            }
        }
        return dp[num.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println("26 have " + decode("26"));
        System.out.println("123 have " + decode("123"));
        System.out.println("121204897635 have " + decode("121204897635"));
    }
}
