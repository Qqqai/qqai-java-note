package qqai.suanfa.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author qqai
 * @createTime 2020/12/15 22:55
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int leftp = left;
        int rightp = mid + 1;
        int i = 0;
        while (leftp <= mid && rightp <= right) {
            help[i++] = arr[leftp] > arr[rightp] ? arr[leftp++] : arr[rightp++];
        }
        while (leftp <= mid) {
            help[i++] = arr[leftp++];
        }
        while (rightp <= right) {
            help[i++] = arr[rightp++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }
}
