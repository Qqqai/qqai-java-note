package qqai.suanfa.some;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/12/2 00:33
 * @description：正方形矩阵顺时针旋转90°
 */

public class Rotate {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println(Arrays.deepToString(rotate(arr)));
    }

    private static int[][] rotate(int[][] arr) {
        // 左上角表示行
        int tr = 0;
        // 左上角表示列
        int tc = 0;
        // 右下角表示行
        int dr = arr.length - 1;
        // 右下角表示列
        int dc = arr[0].length - 1;
        while (tr < dr) {
            rotateEdge(arr, tr++, tc++, dr--, dc--);
        }
        return arr;
    }

    private static void rotateEdge(int[][] arr, int tr, int tc, int dr, int dc) {
        int times = dc - tc;
        int temp = 0;
        for (int i = 0; i != times; i++) {
            // 第一次就是 arr[0][0]  第二次就是[0][1]...
            temp = arr[tr][tc + i];
            // 把他该放的数换上来 一下同理
            arr[tr][tc + i] = arr[dr - i][tc];
            arr[dr - i][tc] = arr[dr][dc - i];
            arr[dr][dc - i] = arr[tr + i][dc];
            arr[tr + i][dc] = temp;
        }
    }
}
