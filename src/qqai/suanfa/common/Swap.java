package qqai.suanfa.common;

/**
 * 交换
 *
 * @author qqai
 * @createTime 2020/12/15 21:22
 */
public class Swap {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
