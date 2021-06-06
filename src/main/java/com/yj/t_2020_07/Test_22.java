package com.yj.t_2020_07;

public class Test_22 {

    /**
     * 时间复杂度:
     * **O(n^2)
     * 空间复杂度:
     * **O(n^2)
     *
     * @param s
     * @return
     */
    public String longestPailndrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = new String();
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                if (dp[i][j] && len + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Test_22().longestPailndrome("jljlljljljljldfdfdfdff"));
        System.out.println(new Test_22().longestPalindrome2("jljlljljljljldfdfdfdff"));
    }

    /**
     * 时间复杂度：O(n^2)，其中 n是字符串的长度。长度为 1和 2的回文中心分别有 n和 n−1个，每个回文中心最多会向外扩展 O(n)次。
     * <p>
     * 空间复杂度：O(1)。
     *
     * @param s
     * @return
     * @author LeetCode
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = this.expandAroundCenter(s, i, i);
            int len2 = this.expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                /*
                 * 当len为奇数时: 比如7,代表元素i前面有3个,后面有3个;
                 * 当len为偶数时: 比如4,代表i前面有1个，后面有2个;
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        /*
         * R是符合条件的元素前一个位置
         * L是符合条件的元素后一个位置
         * 所以R-L-1符合条件的回文字符串的长度
         */
        return R - L - 1;
    }
}
