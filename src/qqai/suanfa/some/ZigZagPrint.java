package qqai.suanfa.some;

/**
 * @author qqai
 * @createTime 2020/12/2 14:33
 * @description：之字形打印数组 笔记 src/qqai/suanfa/img/之字形打印二维数组.png
 */

public class ZigZagPrint {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        printMatrixZigZag(arr);
    }

    private static void printMatrixZigZag(int[][] arr) {
        int ar = 0;
        int ac = 0;
        int bc = 0;
        int br = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean fromUp = false;
        // 如果a来到了最后一行循环停止
        while (ar != endR + 1) {
            printLevel(arr, ar, ac, br, bc, fromUp);
            // 标记 一定要先判断行在判断列
            ar = ac == endC ? ar + 1 : ar;
            ac = ac == endC ? ac : ac + 1;
            // 标记 一定要先判断列在判断行
            bc = br == endR ? bc + 1 : bc;
            br = br == endR ? br : br + 1;
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] arr, int ar, int ac, int br, int bc, boolean fromUp) {
        if (fromUp) {
            while (ar != br + 1) {
                System.out.print(arr[ar++][ac--] + "\t");
            }
        } else {
            while (br != ar - 1) {
                System.out.print(arr[br--][bc++] + "\t");
            }
        }
    }
}
