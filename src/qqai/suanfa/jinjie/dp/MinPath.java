package qqai.suanfa.jinjie.dp;

/**
 * 最小路径和
 *
 * @author qqai
 * @createTime 2020/12/15 17:43
 */
public class MinPath {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24}
        };
        arr = new int[][]{
                {1, 1, 2, 2, 2, 2},
                {2, 1, 1, 2, 2, 2},
                {2, 2, 1, 1, 2, 2},
                {2, 2, 2, 1, 1, 1}
        };
        int path = walk(arr, 0, 0);
        System.out.println(path);
    }

    /**
     * TODO
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int walkDp(int[][] arr, int i, int j) {
        // 如果本身就在右下角那么就不用动
        if (i == arr.length - 1 && j == arr[i].length - 1) {
            return arr[i][j];
        }
        int[][] dp = new int[arr.length][arr[0].length];

        return 1;
    }

    /**
     * 从i j 位置到右下角的最小路径和  标记 暴力递归
     *
     * @param arr 矩阵数组
     * @param i   起始位置
     * @param j   起始位置
     * @return 路径权值
     */
    private static int walk(int[][] arr, int i, int j) {
        // 如果本身就在右下角那么就不用动
        if (i == arr.length - 1 && j == arr[i].length - 1) {
            return arr[i][j];
        }
        // 如果已经到了最后一行 就只需要移动列
        if (i == arr.length - 1) {
            return arr[i][j] + walk(arr, i, j + 1);
        }
        // 如果已经到了最后一列 就只需要移动行
        if (j == arr[0].length - 1) {
            return arr[i][j] + walk(arr, i + 1, j);
        }
        // 可以往右边走 计算出 右边位置到右下角的最短路径和
        int right = walk(arr, i, j + 1);
        // 可以往下边走 计算出下边位置到右下角的最短路径和
        int down = walk(arr, i + 1, j);
        // 比较找到最小的 加上本位置的数就是最小路径和
        return arr[i][j] + Math.min(right, down);
    }
}
