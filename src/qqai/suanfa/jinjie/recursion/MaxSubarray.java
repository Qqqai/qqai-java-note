package qqai.suanfa.jinjie.recursion;

/**
 * 加和最大的连续子序列子序列
 *
 * @author qqai
 * @createTime 2020/12/22 17:03
 */
public class MaxSubarray {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubarrayMarge(arr));
    }

    /**
     * 分治思想
     *
     * @param arr source array
     * @return max value
     */
    private static int maxSubarrayMarge(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // recursion start
        return process(arr, 0, arr.length);
    }

    /**
     * 获取每一个分段连续子序列的最大值 [start, end)
     *
     * @param arr   source array
     * @param start start place
     * @param end   end - 1 is array's indexes end place
     * @return max value in start to end
     */
    private static int process(int[] arr, int start, int end) {
        // if difference less than 2 return arr[start]
        if (end - start < 2) return arr[start];
        // mid = (start + end) / 2
        int mid = (start + end) >> 1;
        // max value in left interval
        int leftMax = Integer.MIN_VALUE;
        // each sum
        int sum = 0;
        for (int i = mid - 1; i >= start; i--) {
            // mid - 1 to start add all and get max value
            sum += arr[i];
            // compare max value
            leftMax = Integer.max(leftMax, sum);
        }
        // max value in right interval
        int rightMax = Integer.MIN_VALUE;
        // reset each sum
        sum = 0;
        for (int i = mid; i < end; i++) {
            // mid to end add all and get max value
            sum += arr[i];
            // compare max value
            rightMax = Integer.max(rightMax, sum);
        }
        // compare and return max value in left interval and right interval and max value maybe in [ start, [i...mid...j) , end)
        return Integer.max(leftMax + rightMax,
                Integer.max(process(arr, start, mid), process(arr, mid + 1, end))
        );
    }

    /**
     * 找到连续子序列中加和最大的值 time complexity is O(n^3)
     *
     * @param arr source array
     * @return max value
     */
    private static int maxSubarray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 双指针暴力穷举 [start, end]; 区间计算
        // max = global max sum
        int max = Integer.MIN_VALUE;
        // from 0 to arr.length
        for (int start = 0; start < arr.length; start++) {
            // from start to arr.length
            for (int end = start; end < arr.length; end++) {
                // local add count
                int sum = 0;
                // start to end  add all
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                }
                // compare max and sum get big one
                max = Integer.max(sum, max);
            }
        }
        return max;
    }
}
