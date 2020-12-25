package qqai.test;

import qqai.suanfa.common.Swap;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/12/25 23:31
 */
public class Test07 {
    public static void main(String[] args) {
        try {
            throw new RuntimeException("你不对劲");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                throw new RuntimeException("你对劲了！");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // right code ...
                int[] arr = {2, 3, 4, 5, 6};
                reverse(arr, 0, arr.length - 3);
                System.out.println(Arrays.toString(arr));
            }
        }
    }
//    public static void main(String[] args) {
//        int[] arr = {2, 3, 4, 5, 6};
//        reverse(arr, 0, arr.length - 3);
//        System.out.println(Arrays.toString(arr));
//    }

    private static void reverse(int[] arr, int L, int R) {
        if (arr == null || L < 0 || R >= arr.length || L > R) throw new RuntimeException("error");
        while (L < R) {
            Swap.swap(arr, L, R);
            L++;
            R--;
        }
    }
}
