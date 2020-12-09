package qqai.suanfa.jinjie;

import java.util.LinkedList;

/**
 * @author qqai
 * @createTime 2020/12/9 20:14
 * @description：子数组详解等于num的问题
 */

public class AllLessNumSubArray {

    public static int getNumWindow(int[] arr, int num) {
        int count = 0;
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int L = 0;
        int R = 0;
        while (L < arr.length) {
            // 找到i - j序列上的最大值和最小值
            while (R < arr.length) {
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            // 如果最大值是i位置的数 L要向右移动  那么就需要这个数的下表出队  如果不是最大值 就没必要出队  因为没影响
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            // 同理
            if (qMin.peekFirst() == L) {
                qMin.pollFirst();
            }
            // L-R上的子序列个数就是 R - L
            count += R - L;
            L++;
        }
        return count;
    }

    public static int getNumBaoLi(int[] arr, int num) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                boolean valid = isValid(arr, i, j, num);
                if (valid) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        return max - min <= num;
    }
}
