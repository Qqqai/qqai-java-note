package qqai.suanfa;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/12 22:21
 * @description：归并求小和问题
 */

public class GuiBingQiuXiaoHe {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(smallSum(arr));
    }


    /*标记 递归排序*/
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return sortProcess(arr, 0, arr.length - 1);
    }

    private static int sortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1); // (left + right) / 2
        return sortProcess(arr, left, mid) +
                sortProcess(arr, mid + 1, right) +
                merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;
        /*
            6  7  8  1  2  3
            1  2  3  4  5  6
            p1       p2
            谁小填谁  当while跳出的时候说明一定是有且只有一个指针越界了
        */
        while (p1 <= mid && p2 <= right) {
            res += arr[p1] < arr[p2] ? arr[p1] * (right - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 标记 两个while虽然是顺序执行但是上一步越界判断是有且只有一个指针会越界，所以这两个while只会有一个会执行
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        // 标记 回填数据
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
        return res;
    }
}
