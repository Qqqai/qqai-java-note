package qqai.leetcode;

import java.util.*;

/**
 * 描述：两数求和
 *
 * @author qqai
 * @createTime 2020-09-16 16:11
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

public class TwoSum {
    /*2、*/
    public static int[] twoSumFuncTow(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            int from = target - a[i];
            for (int j = 0; j < a.length; j++) {
                if (j != i && from == a[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("not find");
    }

    /*1、*/
    public static int[] twoSumFunc(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new RuntimeException("not find");
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSumFuncTow(a, target);
        System.out.println(Arrays.toString(ints));
    }
}
