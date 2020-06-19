package com.yj.t_2020_06;

import java.util.HashSet;
import java.util.Set;

/*
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Test_6_1 {
    /**
     * 就是从第1个元素依次向后拿,向前面元素一个个比较,如果全不相等,则记录下当前最长子串的长度,
     * 如果相等,则退出,从下一个元素开始。需要注意的是,并不是要向前面所有元素比较,而是应该向有效
     * 元素比较,如果用first记录有效元素的首位(初始0);当当前元素和前面元素有相等的时候,frist应该
     * 更新为相等元素下标的下一个位置.
     * <p/>
     * 用first记录不重复子串的下标(初始为0),maxlength(默认1)记录最长子串的长度
     * 将i初始为1,遍历字符串直至到达末尾
     *    将j初始化为first,循环到i值退出
     *       if s[i] == s[j] ，将first的值更新为 j+1,并退出当层循环;
     *    如果s[i]和从s[first]到s[i-1]都不相等,则当前不重复最长子串的长度和maxlength比较,maxlength取其中最大者
     * 返回maxLength
     * <p/>
     * 时间复杂度：
     *     近似o(n^2);
     * 空间复杂度：
     *     所需要的额外空间不随着问题规模的增大而增大,所以为o(1);
     * <p/>
     * @author 杨吉
     */
    public int lengthOfLongestSubstring(String s) {
        int first = 0;
        int maxLength = 1;
        for (int i = 1; i < s.length(); i++) {
            boolean flag = true;
            for (int j = first; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    first = j + 1;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                int max2 = i - first + 1;
                maxLength = maxLength < max2 ? max2 : maxLength;
            }
        }
        return maxLength;
    }


    /**
     * 滑动窗口
     * <p>
     * 假设我们选择字符串中的第 k个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 rk。
     * 那么当我们选择第k+1个字符作为起始位置时，首先从 k+1到 rk的字符显然是不重复的，并且由于少了原本的第 k个字符，
     * 我们可以尝试继续增大 rk，直到右侧出现了重复字符为止。
     * <p>
     * 我们使用两个指针表示字符串中的某个子串（的左右边界）。其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 rk​；
     * <p>
     * 在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，
     * 但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     * 我们记录下这个子串的长度；
     * <p>
     * 在枚举结束后，我们找到的最长的子串的长度即为答案。
     * <p>
     *
     * @author leetcode
     */
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        Test_6_1 test = new Test_6_1();
        int length = test.lengthOfLongestSubstring("abcabcbb");
        System.out.println("length: " + length);
    }
}
