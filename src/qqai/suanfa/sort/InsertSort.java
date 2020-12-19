package qqai.suanfa.sort;

import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author qqai
 * @createTime 2020/12/15 22:06
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 8, 3, 6, 3, 1, 2};
        insertSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i; j > start; j--) {
                if (arr[j] < arr[j - 1]) {
                    Swap.swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }
}
