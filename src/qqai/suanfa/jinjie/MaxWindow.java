package qqai.suanfa.jinjie;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author qqai
 * @createTime 2020/12/9 19:31
 * @description：窗口问题 双端队列——应用1
 */

public class MaxWindow {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // 窗口双端队列
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        // 循环到数组得结尾
        for (int i = 0; i < arr.length; i++) {
            // 双端队列添加元素
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            // 判断是否超过窗口长度
            if (qMax.peekFirst() == i - w) {
                qMax.pollFirst();
            }
            // 必须要达到窗口得长度才能开始获取最大值
            if (i >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(getMaxWindow(arr, 3)));
    }
}
