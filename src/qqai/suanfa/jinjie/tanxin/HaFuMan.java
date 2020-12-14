package qqai.suanfa.jinjie.tanxin;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 哈夫曼编码
 *
 * @author qqai
 * @createTime 2020/12/14 21:18
 */
public class HaFuMan {

    public static void main(String[] args) {
        int[] arr = {8, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(hfm(arr)));
    }

    /*系统的堆实现*/
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.add(i);
        }
        int cur = 0;
        int sum = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(sum);
        }
        return sum;
    }

    /*小根堆排序*/
    public static int[] heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        // 形成小根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size != 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
        return arr;
    }

    /*哈夫曼树实现  结果是哈夫曼树形成过程中各个位置累加的后的结果*/
    public static int[] hfm(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        // 形成小根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        // 弹出第一个最小的数
        swap(arr, 0, --size);
        // 更新小根堆
        heapify(arr, 0, size);
        // 弹出第二个最小的数
        swap(arr, 0, --size);
        // 计算两个数的和
        int count = arr[size + 1] + arr[size];
        while (size > 0) {
            // 把计算结果放在最近弹出的数的位置
            arr[size] = count;
            // 带上加和更新小根堆
            heapify(arr, 0, size);
            // 弹出最小的数
            swap(arr, 0, size);
            // 更新小根堆
            heapify(arr, 0, size);
            // 再次弹出一个最小的数
            swap(arr, 0, --size);
            // 保存一下上次的count 然后放在相加之前的第一此弹出的位置
            int temp = count;
            // 把弹出的第二个小的数放在最近一次弹出的放的位置
            count = arr[size] + arr[size + 1];
            // 放原本的count
            arr[size + 1] = temp;
            // 把count放在第二次弹出的位置上
            arr[size] = count;
        }
        return arr;
    }

    private static void heapify(int[] arr, int index, int size) {
        // 左孩子下标
        int left = index * 2 + 1;
        while (left < size) {
            // 返回下表比较小的孩子的下标
            int small = left + 1 < size ? arr[left] < arr[left + 1] ? left : left + 1 : left;
            // 较小的节点的值如果大于根节点的值  就返回根节点的下标
            small = arr[small] > arr[index] ? index : small;
            // 如果下标是index 说明子节点没有比根节点大的直接跳出循环
            if (small == index) break;
            // 没有跳出循环说明子节点比较小  交换
            swap(arr, index, small);
            // 下标改写 往堆的下层延申
            index = small;
            // "small"下标节点的左孩子
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int i) {
        // 根节点要小于子节点
        while (arr[i] < arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
