package com.yj;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
 * 并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class Test3 {
    /**
     * 思路:
     * 额外利用一个int数组,记录nums数组下标的使用状态; 1  - 使用过, 0 - 未使用过;
     * 从nums[0]开始,查看当前状态是否等于1,等于1退出,否则进行下一步;
     * 从当前元素的下一个位置遍历数组,比较两个数是否等于target,不等于target则退出,否则进行下一步;
     * 记录下两个数的下标，并将其状态置为1;
     * <p>
     * 时间复杂度 O(n^2);
     * 空间复杂度O(n);
     *
     * @param nums
     * @param target
     * @return
     * @author 杨吉
     */
    public int[] twoSum(int[] nums, int target) {
        int[] status = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target || status[i] == 1) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res.add(i);
                    res.add(j);
                    status[i] = 1;
                    status[j] = 1;
                }
            }
        }
        System.out.println(res.toString());
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res2[i] = res.get(i);
        }

        return res2;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] res = new Test3().twoSum(nums, 18);

        for (int i = 0; i < res.length; i++) {
//            System.out.printf("%d", res[i]);
//            System.out.println(res[i] + ',');  // 不行,输出的时候不能加字符，会把字符转成整型，然后在相加
            System.out.print(res[i] + ",");
        }
    }

    class solution {
        /**
         * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。
         * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
         * <p>
         * 通过以空间换取速度的方式，我们可以将查找时间从 O(n)降低到 O(1)。哈希表正是为此目的而构建的，
         * 它支持以 近似 恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 OO(n)。
         * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
         * <p>
         * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
         * 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target−nums[i]target - nums[i]target−nums[i]）是否存在于表中。注意，该目标元素不能是 nums[i]nums[i]nums[i] 本身！
         * <p>
         * 时间复杂度：O(n)，
         * 我们把包含有 nnn 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1) ，所以时间复杂度为 O(n)。
         * <p>
         * 空间复杂度：O(n)，
         * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素。
         *
         * @param nums
         * @param target
         * @return
         * @author LeetCode
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[]{i, map.get(complement)};
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
