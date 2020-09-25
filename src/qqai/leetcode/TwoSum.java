package qqai.leetcode;

import java.util.*;

/**
 * 描述：两数求和
 *
 * @author qqai
 * @createTime 2020-09-16 16:11
 */

public class TwoSum {
    public TwoSum(int[] a, int target) {
    }

    public static int[] twoSumFunc(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new RuntimeException("not find");
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSumFunc(a, target);
        System.out.println(Arrays.toString(ints));
    }
}
