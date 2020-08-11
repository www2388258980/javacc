package com.data.structure.test.Line;

public class Chapter4_1 {
    /**
     * BF算法
     * <p>
     * 分别利用计数指针i和j指示主串s和模式t当前正待比较的字符串,i初始值为pos,j初值为0;
     * 如果两个串均为比较到末尾，则循环执行一下操作:
     * **** s[i]和t[j]比较，若相等，则i和j分别指示串中下个位置,继续比较后续字符
     * **** 若不等,指针后退重新开始匹配,从主串的下一个字符(i= i - j + 1)起重新和模式的第一个字符(j = 0)比较
     * 如果j = t.length,说明模式t中的每个字符依次和主串s中的一个连续字符序列相等,则匹配成功,返回模式t中的第一个字符
     * 在主串s中的序号(i - t.length),否则匹配不成功，返回-1
     * <p>
     * 时间复杂度:
     * 最好: O(n+m) 最坏: O(n*m)
     *
     * @param s
     * @param t
     * @param pos
     * @return
     */
    public int inddex_BF(String s, String t, int pos) {
        int i = pos;
        int j = 0;
        if (i < 0) {
            i = 0;
        }
        if (s == null || "".equals(s) || t == null || "".equals(t)) {
            return -1;
        }
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == t.length()) {
            // i为上次比较字符的下一个位置
            return i - t.length();
        }
        return -1;
    }

    /**
     * @param s
     * @param t
     * @param pos
     * @return
     */
    public int index_kmp(String s, String t, int pos) {
        return -1;
    }

    /**
     * kmp算法的核心是当匹配失败时,主串指针i不回溯,模式串指针j回溯
     * 模式串t回溯和前缀子字符串和后缀子字符串相关
     * 规定next[0] = -1,next[1] = 0
     * 下标n(1<n<t.length)的最长前后缀长度,假设t[n-1]的最长前后缀长度为m
     * ** if t[n-1] == t[m],next[n] = m+1;
     * ** if t[n-1] != t[m],假设t[m]的最长前后缀长度为u,if t[u] == t[m],则next[n] = u + 1,否则继续比较,直到next[0] = -1
     *
     * @param t
     * @return
     */
    public int[] getNext(String t) {
        if (t == null) {
            return new int[0];
        }
        if (t.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[t.length()];
        next[0] = -1;
        next[1] = 0;
        int n = 2;
        // m代表n前面一个字符的最长相等前缀的长度，最开始n=2,next[n-1] = 0,所以m的初始值是0；
        int m = 0;
        while (n < t.length()) {
            if (t.charAt(n - 1) == t.charAt(m)) {
                next[n++] = ++m;
            } else if (m > 0) {
                m = next[m];
            } else {
                next[n++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int i = new Chapter4_1().inddex_BF("ababcabcacbab", "abcac", 0);
        System.out.println("i: " + i);
    }
}
