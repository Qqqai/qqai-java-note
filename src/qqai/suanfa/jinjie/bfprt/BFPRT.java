package qqai.suanfa.jinjie.bfprt;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/12/8 21:52
 * @description：在无序数组中找到第k小的数
 */

public class BFPRT {
    private static int count = 0;

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 7, 8, 9, 7, 5, 3, 6, 8, 3, 5, 8, 4, 10};
        int k = 9;
//        System.out.println(partition(arr, k, 0, arr.length - 1, arr[(int) (Math.random() * arr.length - 1)]));
        arr = new int[]{1, 3, 5, 6, 7, 8, 9, 7, 5, 3, 6, 8, 3, 5, 8, 4, 10};
        System.out.println(bfprt(arr, k, 0, arr.length - 1));
        arr = new int[]{1, 3, 5, 6, 7, 8, 9, 7, 5, 3, 6, 8, 3, 5, 8, 4, 10};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int bfprt(int[] arr, int k, int begin, int end) {
        if (begin == end) {
            return arr[begin];
        }
        // 中位数组
        int pivot = medianOfMedians(arr, begin, end);
        // 荷兰数划分
        int[] pivotRange = partition(arr, begin, end, pivot);
        // 如果在等于得区间内 直接返回
        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
            // 不在等于得区间内则分情况
        } else if (k < pivotRange[0]) {
            // 在小于区间
            return bfprt(arr, k, begin, pivotRange[0] - 1);
        } else {
            // 在大于区间
            return bfprt(arr, k, pivotRange[1] + 1, end);
        }
    }

    /*荷兰数*/
    private static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur < big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, --big, cur);
            } else {
                cur++;
            }
        }
        System.out.println(Arrays.toString(arr) + " -->cur = " + cur + " -->small = " + small + " -->big = " + big + " --> time = " + count + " --> " + pivotValue);
        return new int[]{small + 1, big - 1};
    }

    private static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        // 创建一个数组长度是分组得得组数
        int[] marr = new int[num / 5 + offset];
        for (int i = 0; i < marr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            // 获取每一组得中位数
            marr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        // 得到这个数组得中位数
        return bfprt(marr, marr.length / 2, 0, marr.length - 1);
    }

    /*获取每一组得中位数*/
    private static int getMedian(int[] arr, int begin, int end) {
        // 插入排序
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = sum / 2 + sum % 2;
        return arr[mid];
    }

    /*插入排序*/
    private static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /*荷兰数*/
    private static int partition(int[] arr, int k, int left, int right, int num) {
        if (arr == null || arr.length == 1 || right <= 0) {
            return -1;
        }
        int l = left;
        int r = right;
        int less = left - 1;
        int more = right + 1;
        while (left < more) {
            if (arr[left] < num) {
                swap(arr, left++, ++less);
            } else if (arr[left] > num) {
                swap(arr, left, --more);
            } else {
                left++;
            }
        }
        System.out.println(Arrays.toString(arr) + " --> time = " + ++count + " --> num = " + num);
        if (less < k - 1 && k - 1 < more) {
            return arr[k - 1];
        } else if (k - 1 <= less) {
            return partition(arr, k, l, less, arr[l + (int) (Math.random() * less)]);
        } else {
            return partition(arr, k, more, r, arr[r - more + (int) (Math.random() * more)]);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
