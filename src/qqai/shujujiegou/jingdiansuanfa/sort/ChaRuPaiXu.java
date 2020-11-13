package qqai.shujujiegou.jingdiansuanfa.sort;

import java.util.Arrays;

/**
 * 描述：直接插入排序
 *
 * @author qqai
 * @createTime 2020-09-25 15:49
 */

public class ChaRuPaiXu {
    public static void main(String[] args) {
        int[] old = {5, 4, 6, 7, 354, 5644, 534, 5435, 534543754, 3, 3, 6, 4, 9, 6};
        for (int i = 1; i < old.length; i++) {
            for (int j = i - 1; j >= 0 && old[j] > old[j + 1]; j++) {
                swap(old, j, j + 1);
            }
        }
        System.out.println(Arrays.toString(old));
    }

    public static void sort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = arr[i];
    }

}
