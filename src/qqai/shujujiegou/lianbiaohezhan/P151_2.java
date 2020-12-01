package qqai.shujujiegou.lianbiaohezhan;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/29 17:24
 * @description：
 */

public class P151_2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 4;
        System.out.println(Arrays.toString(reorder(arr, k)));
    }

    private static int[] reorder(int[] arr, int k) {
        // 每次只后移一位  k次就是后移了k位
        for (int i = 0; i < k; i++) {
            one(arr);
        }
        return arr;
    }

    private static void one(int[] arr) {
        // temp为每次需要移动的元素的值
        int temp = arr[0];
        // 每次移动一位下标从1开始就是0位置不需要判断直接把0位置换到1位置
        int index = 1;
        // 记录下一个
        int next;
        while (index < arr.length) {
            // 本位置的值
            next = arr[index];
            // 把temp存的上一个位置的值放到本位置
            arr[index] = temp;
            // 把本位置的值放进temp
            temp = next;
            // 下标移动
            index++;
        }
        // 最后一位会存到temp里arr[0]放进最后一位的值
        arr[0] = temp;
    }
}
