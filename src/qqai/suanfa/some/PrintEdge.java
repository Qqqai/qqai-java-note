package qqai.suanfa.some;

/**
 * @author qqai
 * @createTime 2020/12/1 23:51
 * @description：数组螺旋遍历
 */

public class PrintEdge {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        sprialOrderPrint(arr);
    }

    private static void sprialOrderPrint(int[][] arr) {
        // 左上角表示行
        int tr = 0;
        // 左上角表示列
        int tc = 0;
        // 右下角表示行
        int dr = arr.length - 1;
        // 右下角表示列
        int dc = arr[0].length - 1;
        while (tr <= dr && tc <= dc) {
            printEdge(arr, tr++, tc++, dr--, dc--);
        }
    }

    // 标记 img-> src/qqai/suanfa/img/螺旋遍历的原理.png
    private static void printEdge(int[][] arr, int tr, int tc, int dr, int dc) {
        // 特殊情况一  只有一行
        if (tr == dr) {
            for (int i = tc; i <= dc; i++) {
                System.out.print(arr[tr][i] + "\t");
            }
            // 只有一列
        } else if (tc == dc) {
            for (int i = tr; i <= dr; i++) {
                System.out.print(arr[i][tc] + "\t");
            }
            // 非特殊情况 一圈都要遍历
        } else {
            int curC = tc;
            int curR = tr;
            // tr行 从 tc -> dc
            while (curC != dc) {
                System.out.print(arr[tr][curC++] + "\t");
            }
            // dc列 从tr -> dr
            while (curR != dr) {
                System.out.print(arr[curR++][dc] + "\t");
            }
            // dr行 从dc -> tc
            while (curC != tc) {
                System.out.print(arr[dr][curC--] + "\t");
            }
            // tc列 从dr -> tr
            while (curR != tr) {
                System.out.print(arr[curR--][tr] + "\t");
            }
        }
    }
}
