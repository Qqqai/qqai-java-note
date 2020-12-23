package qqai.suanfa.sort;

import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * 快速排序 不稳定的排序
 *
 * @author qqai
 * @createTime 2020/12/16 00:07
 */
public class QuickSortOne {
    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 6, 7, 8, 3, 1, 9};
        quicklySort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quicklySort(int[] arr, int left, int right) {
        if (left < right) {
            //　标记 随机快排 因为partition用的是最后一个值作为基准值，所以就在right-left之间选择一个数和right交换位置，基准值就不确定了
            Swap.swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quicklySort(arr, 0, p[0]);
            quicklySort(arr, p[1], right);
        }
    }

    /*荷兰数交换 标记 标准的荷兰数交换  把最后一个数也当作荷兰数的排序元素算进去了*/
    private static int[] partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right + 1;
        int num = arr[right];
        while (left < more) {
            if (arr[left] < num) {
                Swap.swap(arr, left++, ++less);
            } else if (arr[left] > num) {
                Swap.swap(arr, left, --more);
            } else {
                left++;
            }
        }
        return new int[]{less, more};
    }
}
