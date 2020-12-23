package qqai.suanfa.jinjie.dp;

import java.util.Arrays;

/**
 * 机器人走格子
 * 机器人在 索引0位置只能走向索引1位置
 * 机器人在 索引n位置只能走向n-1位置
 * 机器人在中间位置 能走向两边
 * 给定走的步数和要到达的顶点求能有多少种走法
 * <p>
 * 笔记 动态规划：
 * 笔记   一个问题暴力递归的过程抽象出来就是动态规划
 *
 * @author qqai
 * @createTime 2020/12/23 22:20
 */
public class RobotWalk {
    public static void main(String[] args) {
        int n = 7;
        int m = 3;
        int k = 3;
        int p = 2;
        System.out.println(ways(n, m, k, p));
    }

    /**
     * 暴力递归
     *
     * @param n n个位置
     * @param m 开始在m位置
     * @param k 走k步
     * @param p 到达p位置
     * @return 多少种走法
     */
    private static int ways(int n, int m, int k, int p) {
        if (n < 2 || k < 1 || m < 1 || p < 1 || p > n) {
            return -1;
        }
        int[][] dp = new int[n + 1][k + 1];
        // dp数组初始化全为-1;
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
//        return walk2(n, m, k, p, dp);
//        return walk(n, m, k, p);
        return dp(n, m, k, p);
    }

    /**
     * 动态规划
     *
     * @param n 格子数
     * @param m 起始点
     * @param k 可以走多少步
     * @param p 到达p点
     * @return 可选方案
     */
    private static int dp(int n, int m, int k, int p) {
        // n 7  m 3  k 3  p 2
        int[][] dp = new int[n + 1][k + 1];
        // base case    if (rest == 0) return cur == p ? 1 : 0;
        dp[p][0] = 1;
        // 以列为空指遍历每一层的cur  rest从1开始  因为rest = 0时 只有dp[p][0] = 1 其余全部为0
        for (int rest = 1; rest < k + 1; rest++) {
            // cur根据题意不能小于1
            for (int cur = 1; cur < n + 1; cur++) {
                // 递归的改写
                // if (cur == 1)  return walk(n, 2, rest - 1, p);
                if (cur == 1) dp[cur][rest] = dp[2][rest - 1];
                    // if (cur == n) return walk(n, n - 1, rest - 1, p);
                else if (cur == n) dp[cur][rest] = dp[cur - 1][rest - 1];
                    //
                else dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
        }
        // 返回的是 m位置经过k步可以到p点的方案数
        return dp[m][k];
    }

    /**
     * 记忆化搜索
     *
     * @param n    n个位置
     * @param cur  当前位置
     * @param rest 还剩多少步可以走
     * @param p    要到达的位置
     * @param dp   记忆数组
     * @return 走法
     */
    private static int walk2(int n, int cur, int rest, int p, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 如果剩余步数为0就不能走了
        if (rest == 0) {
            // 当前cur在不在p位置 在的话就是一种解法 不在就是错误方案
            dp[cur][rest] = cur == p ? 1 : 0;
            return dp[cur][rest];
        }
        // 如果当前cur在1位置 那么它下一步只能到2位置 并且只剩下rest-1步可以走
        if (cur == 1) {
            dp[cur][rest] = walk(n, 2, rest - 1, p);
            return dp[cur][rest];
        }
        // 如果当前在n位置那么只能往n-1位置走并且只剩下rest-1步
        if (cur == n) {
            dp[cur][rest] = walk(n, n - 1, rest - 1, p);
            return dp[cur][rest];
        }
        // 如果在中间   往右走 和  往左走的结果要加起来 求出总的方法和
        dp[cur][rest] = walk(n, cur + 1, rest - 1, p) + walk(n, cur - 1, rest - 1, p);
        // 相当于把暴力递归的返回值加入缓存加入缓存
        return dp[cur][rest];
    }

    /**
     * 暴力递归
     *
     * @param n    n个位置
     * @param cur  当前位置
     * @param rest 还剩多少步可以走
     * @param p    要到达的位置
     * @return 走法
     */
    private static int walk(int n, int cur, int rest, int p) {
        // 如果剩余步数为0就不能走了
        if (rest == 0) {
            // 当前cur在不在p位置 在的话就是一种解法 不在就是错误方案
            return cur == p ? 1 : 0;
        }
        // 如果当前cur在1位置 那么它下一步只能到2位置 并且只剩下rest-1步可以走
        if (cur == 1) {
            return walk(n, 2, rest - 1, p);
        }
        // 如果当前在n位置那么只能往n-1位置走并且只剩下rest-1步
        if (cur == n) {
            return walk(n, n - 1, rest - 1, p);
        }
        // 如果在中间   往右走 和  往左走的结果要加起来 求出总的方法和
        return walk(n, cur + 1, rest - 1, p) + walk(n, cur - 1, rest - 1, p);
    }
}
