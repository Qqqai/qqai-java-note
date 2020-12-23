package qqai.suanfa.jinjie.dp;

/**
 * 矩阵到右下角有多少种走法
 *
 * @author qqai
 * @createTime 2020/12/23 11:24
 */
public class HowManyPath {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 1, 2, 2, 2, 2},
                {2, 1, 1, 2, 2, 2},
                {2, 2, 1, 1, 2, 2},
                {2, 2, 2, 1, 1, 1}
        };
        int path = howManyPath(arr);
        System.out.println(path);
    }

    /**
     * 有多少种走法
     *
     * @param arr source array
     * @return how many path
     */
    private static int howManyPath(int[][] arr) {
        // arr is not allowed null
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // recursive start
        return walk(arr, 0, 0);
    }

    /**
     * recursive method
     *
     * @param arr source array
     * @param i   start i
     * @param j   start j
     * @return how many path in (i, j) to lower right corner
     */
    private static int walk(int[][] arr, int i, int j) {
        // is lower right corner
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return 1;
        }
        // in last row
        if (i == arr.length - 1) {
            return walk(arr, i, j + 1);
        }
        // in last column
        if (j == arr[0].length - 1) {
            return walk(arr, i + 1, j);
        }
        // to right
        int right = walk(arr, i, j + 1);
        // to down
        int down = walk(arr, i + 1, j);
        // path count
        return right + down;
    }
}
