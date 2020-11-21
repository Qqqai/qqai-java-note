package qqai.java.base;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author qqai
 * @createTime 2020/11/7 23:18
 * @description：Arrays
 */

public class ArraysTest {

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {2, 3, 4, 5}, {1, 1, 1, 1}, {1, 2, 3, 7}};
        int[][] res = sort(a);
        System.out.println(Arrays.deepToString(res));
    }

    private static int[][] sort(int[][] a) {
        int[][] b = new int[a.length][5];
        for (int i = 0; i < a.length; i++) {
            b[i][0] = a[i][0];
            b[i][1] = a[i][1];
            b[i][2] = a[i][2];
            b[i][3] = a[i][3];
            b[i][4] = a[i][0] + a[i][1] + a[i][2] + a[i][3];
        }
//        int[] temp;
//        for (int i = 0; i < b.length; i++) {
//            for (int j = i; j < b.length; j++) {
//                if (b[i][4] > b[j][4]) {
//                    temp = b[i];
//                    b[i] = b[j];
//                    b[j] = temp;
//                }
//            }
//        }

        Arrays.sort(b, Comparator.comparingInt(o -> o[4]));

        return b;
    }

}
