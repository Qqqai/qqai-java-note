package qqai.suanfa.sort;

import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * 选择排序 不稳定的排序
 *
 * @author qqai
 * @createTime 2020/12/15 21:12
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 2, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                max = arr[j] >= arr[max] ? j : max;
            }
            Swap.swap(arr, max, i - 1);
        }
    }
}
