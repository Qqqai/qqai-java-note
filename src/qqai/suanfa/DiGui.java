package qqai.suanfa;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/12 20:12
 * @description：递归
 */

public class DiGui {
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 6};
        System.out.println(getMax(arr, 0, arr.length - 1));

        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*标记 归并排序*/
    public static int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = (L + R) / 2;
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    /*标记 递归排序*/
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    private static void sortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1); // (left + right) / 2
        sortProcess(arr, left, mid);
        sortProcess(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        /*
            6  7  8  1  2  3
            1  2  3  4  5  6
            p1       p2
            谁小填谁  当while跳出的时候说明一定是有且只有一个指针越界了
        */
        while (p1 <= mid && p2 <= right) {
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
    }
}
