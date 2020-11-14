package qqai.suanfa;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/13 18:42
 * @description：荷兰数
 */

public class HeLanShu {

    public static void main(String[] args) {
        int[] a = {7, 3, 2, 4, 2, 3};
        System.out.println(Arrays.toString(classifyNum(a, 4)));
        System.out.println(Arrays.toString(partition(a, 3, 0, a.length - 1)));
    }

    /*小于的放左边，大于的放右边*/
    public static int[] classifyNum(int[] arr, int num) {
        int x = 0;
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= num) {
                temp = arr[x];
                arr[x++] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    /*标记 荷兰国旗*/
    public static int[] partition(int[] arr, int num, int L, int R) {
        /*
        6  6  3  2  4  2  6  9  7
    -1  1  2  3  4  5  6  7  8  9
   less L                       R  more
     →                              ←
        */
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < num) {
                swap(arr, ++less, L++);
            } else if (arr[L] > num) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
