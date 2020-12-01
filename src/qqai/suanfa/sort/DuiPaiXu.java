package qqai.suanfa.sort;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/12/1 00:47
 * @description：堆排序
 */

/*
笔记
    大根堆； 首先是一个完全二叉树，其次在这个二叉树中任何一颗子树的最大值都是根节点
    小根堆： 相反任何一棵子树的最小值都是根节点

 */
public class DuiPaiXu {
    public static void main(String[] args) {
        int[] arr = {4, 4, 6, 3, 5, 1, 7, 9, 3, 6};
        int[] res = heapSort(arr);
        System.out.println(Arrays.toString(res));
    }

    /* 标记 堆排序*/
    private static int[] heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        // 每次把数组中前i位做成大根堆 循环完成 整个数组也就形成了一个大根堆  复杂度 O(N)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
        return arr;
    }

    // 大根堆形成的时候子节点比根节点大的时候交换的过程，子节点会一直网上找，直到根节点大于子节点的时候停止
    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    // 当一个大根堆某个节点得值变小后要变形成为新的大根堆执行的方法
    private static void heapify(int[] arr, int index, int heapSize) {
        // index的左孩子的下标
        int left = index * 2 + 1;
        // 保证不越界
        while (left < heapSize) {
            // left + 1 是右孩子的下标  返回的也是最大的那个值得下表
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            // 上一步得到得最大值，和index位置得值比较 谁大largest记录谁得下标
            largest = arr[largest] > arr[index] ? largest : index;
            // 如果index位置比较大 那么就不用交换 结束循环
            if (largest == index) {
                break;
            }
            // 交换
            swap(arr, largest, index);
            // index成为较大孩子得下标
            index = largest;
            // left变为下一个子树得左子孩子
            left = index * 2 + 1;
        }
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
