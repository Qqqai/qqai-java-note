package qqai.test;

import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author qqai
 * @createTime 2020/12/13 16:14
 */

public class Test06 {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 形成堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        Swap.swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapfiy(arr, 0, heapSize);
            Swap.swap(arr, 0, --heapSize);
        }
    }

    private static void heapfiy(int[] arr, int i, int size) {
        int left = i * 2 + 1;
        while (left < size) {
            int max = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
            max = arr[max] > arr[i] ? max : i;
            if (i == max) break;
            Swap.swap(arr, i, max);
            i = max;
            left = i * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[getParentIndex(i)]) {
            Swap.swap(arr, i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public static int getParentIndex(int i) {
        return (i - 1) / 2;
    }
}
