package qqai.suanfa.some.i;

/**
 * @author qqai
 * @createTime 2020/12/1 15:30
 * @description：桶 标记 求数组中排完序之后相邻两个数差值得最大值
 */

public class Tong {
    public static void main(String[] args) {
        int[] arr = {4, 4, 6, 3, 5, 1, 7, 9, 3, 6};
        int res = maxGap(arr);
        System.out.println(res);
    }

    private static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 找到数组得最大最小值
        for (int i : arr) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }
        if (min == max) {
            return 0;
        }
        // 三个数组同一位置代表一个桶的最大值，最小值，是否存在数据
        boolean[] hasNums = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            // 计算当前数据应该在那个桶
            bid = bucket(arr[i], len, min, max);
            // 计算桶中得最大最小值并把flag置为true表示存在值
            mins[bid] = hasNums[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNums[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNums[bid] = true;
        }
        // 最大的差值
        int res = 0;
        // 上一个桶得最大值
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++) {
            // 如果这个桶存在数据  计算
            if (hasNums[i]) {
                // 区的上一次计算得到的最大值和本桶最小值与上一个桶最大值得差值比较
                res = Math.max(res, mins[i] - lastMax);
                // 当前桶得最大值成为 “上一个桶最大值”
                lastMax = maxs[i];
            }
        }
        // 返回这个最大得差值
        return res;
    }

    /*标记 计算当前数据应该在那个桶*/
    private static int bucket(int i, int len, int min, int max) {
        return (i - min) * len / (max - min);
    }
}
