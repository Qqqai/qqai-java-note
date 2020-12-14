package qqai.bishi.bianchengmiao;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/10/26 20:06
 * @description：编程喵
 */

public class Main1 {

    public static void main(String[] args) {
        int[] ints = {6, 4, 3, 5, 8};
        int[] a = sort(ints);
        System.out.println(Arrays.toString(a));
    }

    private static int[] sort(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        return a;
    }

}
