package qqai.suanfa.sort;


import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author qqai
 * @createTime 2020/12/15 21:18
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        for (int j = arr.length; j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (arr[i] < arr[i - 1]) {
                    Swap.swap(arr, i, i - 1);
                }
            }
        }
    }
}
