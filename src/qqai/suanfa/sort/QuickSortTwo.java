package qqai.suanfa.sort;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/30 23:52
 * @description：快速排序
 */

public class QuickSortTwo {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 7, 8, 9, 2, 18};
        int[] res = quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(res));
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //　标记 随机快排 因为partition用的是最后一个值作为基准值，所以就在right-left之间选择一个数和right交换位置，基准值就不确定了
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0]);
            // 标记 p[1] + 1 是more位置后一个位置 相当于把没有交换的那个数直接放到了等于区域
            quickSort(arr, p[1] + 1, right);
        }
        return arr;
    }

    /*标记 荷兰国旗*/
    public static int[] partition(int[] arr, int left, int right) {
        /*
        6  6  3  2  4  2  6  9  7
    -1  1  2  3  4  5  6  7  8  9
   less L                       R
     →                          more
                                ←
        */
        int less = left - 1;
        // 标记 more在right位置上  表示right位置的数并没有参与荷兰数的交换
        int more = right;
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        // 交换大于区域的最后一个元素到more元素的位置上 标记 因为最大区域的最后一个元素并没有参加荷兰数的排序
        swap(arr, more, right);
        // less + 1  把等于区域的第一个元素包含进去
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
