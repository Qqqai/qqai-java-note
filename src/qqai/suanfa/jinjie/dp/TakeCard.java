package qqai.suanfa.jinjie.dp;

/**
 * 拿纸牌 两个人依次从左或右拿纸牌 求获胜玩家的分数
 *
 * @author qqai
 * @createTime 2020/12/23 18:36
 */
public class TakeCard {
    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5, 19, 29, 80, 4};
        System.out.println(win(arr));
        System.out.println(dp(arr));
    }

    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int dp(int[] arr) {
        int N = arr.length;
        // 两个方法互相依赖 就是两个表互相依赖
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        // 初始化
        for (int i = 0; i < f.length; i++) {
            // base case  if (L == R) return arr[L];
            f[i][i] = arr[i];
            // base case if (L == R) return 0;   array default 0
            // s[i][i] = 0;
        }
        // 依次遍历就足够了
        for (int i = 1; i < N; i++) {
            // 表的行
            int L = 0;
            // 表的列
            int R = i;
            // 边界限定
            while (L < N && R < N) {
                // 递归改写
                f[L][R] = Integer.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Integer.min(f[L + 1][R], f[L][R - 1]);
                // 对角线填表
                L++;
                R++;
            }
        }
        // 取值
        return Integer.max(f[0][N - 1], s[0][N - 1]);
    }

    /**
     * 获取最得值
     *
     * @param arr
     * @return
     */
    public static int win(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        return Integer.max(
                // 先手获得的最大值
                f(arr, 0, arr.length - 1),
                // 后手获得的最大值
                s(arr, 0, arr.length - 1));
    }

    /**
     * 先手函数
     *
     * @param arr 牌
     * @param L   左边没拿的第一个
     * @param R   右边没拿的第一个
     * @return 拿了多少
     */
    public static int f(int[] arr, int L, int R) {
        // 只有一个元素了
        if (L == R) {
            return arr[L];
        }
        // 去两个方案的最大值
        return Integer.max(
                // 第一种方案是拿走L然后在L+1到R上变为后手
                arr[L] + s(arr, L + 1, R),
                // 第二个方案是拿走R然后在L到R-1上后手
                arr[R] + s(arr, L, R - 1));
    }

    /**
     * 后手函数
     *
     * @param arr 牌
     * @param L   左边没拿的第一个
     * @param R   右边没拿的第一个
     * @return 拿了多少
     */
    public static int s(int[] arr, int L, int R) {
        // 后手只有一张牌时不能拿走任何牌
        if (L == R) {
            return 0;
        }
        // 这里返回最小是因为 对手在获取的时候一定会把最坏的情况留出来 所以这里直接取最小的
        return Math.min(
                // 第一种方案是对手取了L 然后我们从L+1到R上取
                f(arr, L + 1, R),
                // 第二种情况是对手取了R 然后我们从L到R-1上取
                f(arr, L, R - 1));
    }

}
