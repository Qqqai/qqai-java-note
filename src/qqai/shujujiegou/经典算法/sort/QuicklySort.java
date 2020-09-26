package qqai.shujujiegou.经典算法.sort;

import java.util.Arrays;

/**
 * 描述：快速排序
 *
 * @author qqai
 * @createTime 2020-09-26 10:10
 */

public class QuicklySort {
    public static void main(String[] args) {
        int[] old = {5, 4, 6, 7, 354, 5644, 534, 5435, 534543754, 3, 3, 6, 4, 9, 6};
        sort(old, 0, old.length - 1);
        System.out.println(Arrays.toString(old));
    }

    public static void sort(int[] a, int low, int high) {
        //已经排完
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;

        //保存基准值
        int pivot = a[left];
        while (left < right) {
            //从后向前找到比基准小的元素
            while (left < right && a[right] >= pivot) {
                right--;
            }
            a[left] = a[right];
            //从前往后找到比基准大的元素
            while (left < right && a[left] <= pivot) {
                left++;
            }
            a[right] = a[left];
        }
        // 放置基准值，准备分治递归快排
        a[left] = pivot;
        sort(a, low, left - 1);
        sort(a, left + 1, high);
    }
}
