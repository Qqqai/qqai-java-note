package qqai.suanfa.list.a;

/**
 * 岛屿个数的判断
 *
 * @author qqai
 * @createTime 2020/12/13 21:51
 */

public class CountIsLands {

    /*全遍历思想*/
    public static int countIsLands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    // 一个岛
                    res++;
                    // 把连成一片的1全部变成2  下次遍历就不会再进这个函数操作了
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    /*递归把所有的一整片都是1的全部变为2*/
    private static void infect(int[][] m, int i, int j, int N, int M) {
        // 边界限制
        if (i < 0 || i >= N || j < 0 || j > M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        // 递归
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
    }
}
