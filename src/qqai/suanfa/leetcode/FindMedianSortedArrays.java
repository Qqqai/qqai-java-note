package qqai.suanfa.leetcode;

/**
 * @author qqai
 * @createTime 2020/11/2 23:36
 * @description：
 */

//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
//
//
//
// 示例 1：
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
// 示例 3：
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
//
//
// 示例 4：
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
//
//
// 示例 5：
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// Related Topics 数组 二分查找 分治算法
// 👍 3364 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int index = 0;
        for (int i : nums1) {
            res[index++] = i;
        }
        for (int i : nums2) {
            res[index++] = i;
        }
        Arrays.sort(res);
        if (res.length % 2 == 0) {
            return ((double) (res[res.length / 2 - 1] + res[res.length / 2])) / 2;
        }
        return res[res.length / 2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
