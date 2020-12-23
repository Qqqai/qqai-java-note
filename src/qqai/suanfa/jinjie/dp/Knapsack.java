package qqai.suanfa.jinjie.dp;

/**
 * 0-1背包问题
 *
 * @author qqai
 * @createTime 2020/12/21 21:07
 */
public class Knapsack {
    public static void main(String[] args) {
        int bag = 11;
        int[] w = {3, 2, 4, 7};
        int[] v = {5, 6, 3, 19};
        System.out.println(getMaxValue(v, w, bag));
    }

    private static int getMaxValue(int[] v, int[] w, int bag) {
//        return process(v, w, 0, 0, bag);
        return dp(v, w, bag);
    }

    private static int dp(int[] v, int[] w, int bag) {
        // 填表 行 index  列是 rest  返回的是（0，bag）位置的解
        // base case是当index == w.length时返回0 所以最后一行的值应该全是0
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // 最后一行初始默认就是0 所以不用去可以初始化成0  dp[N][...] = 0  从下面的行往上面的行
        for (int index = N - 1; index >= 0; index--) {
            // 剩余空间从左往右填好每一行列  由于 base case ： if (rest < 0) return -1; 要求就是 rest是可以等于0的
            for (int rest = 0; rest <= bag; rest++) {
                //　把原始的暴力过程拆分成动态规划　　把本位置的值先换成依赖的dp[index + 1][rest]的值
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= w[index]) {
                    // 标记 动态转移方程  笔记 因为本位置的物品可能被拿了也可能没有被拿 所以就有了两个结果 取最大的
                    dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        return dp[0][bag];
    }

    /**
     * 递推过程
     *
     * @param v        价值数组
     * @param w        质量数组
     * @param index    下标
     * @param alreadyW 0~i-1已经装好的重量
     * @param bag      背包容量
     * @return 可获得的最大价值
     */
    public static int process(int[] v, int[] w, int index, int alreadyW, int bag) {
        // 质量越界 标记 相等也没有后续方案
        if (alreadyW > bag) {
            // 没有这种方案
            return -1;
        }
        // 质量没有越界 而且到了最后一个
        if (index == w.length) {
            // 我们需要的是index之后能有多少种方案 但是已经到了最后面了  所以就没有方案 返回0
            return 0;
        }
        // 递推式 index位置的物品可能有两种情况 拿了或者没拿 获得的是i+1可以获取到的价值
        int p1 = process(v, w, index + 1, alreadyW, bag);
        int p2 = process(v, w, index + 1, alreadyW + w[index], bag);
        // 如果p2不是-1
        if (p2 != -1) {
            // 此处价值修改成p2加上本位的价值 因为p2是取了本位的物品的
            p2 = v[index] + p2;
        }
        return Integer.max(p2, p1);
    }

    /**
     * 递推式 原理同上
     *
     * @param v     价值数组
     * @param w     质量数组
     * @param index 下标
     * @param rest  背包还剩下的容量
     * @return 可获得的最大价值
     */
    public static int process(int[] v, int[] w, int index, int rest) {
        // 背包没有容量了 表示没有后续方案
        if (rest < 0) {
            return -1;
        }
        // index 到了最后一个位置的后一个位置  bas case
        if (index == w.length) {
            return 0;
        }
        // 递推式 index位置的物品可能有两种情况 拿了或者没拿 获得的是i+1可以获取到的价值
        int p1 = process(v, w, index + 1, rest);
        int p2 = process(v, w, index + 1, rest - w[index]);
        // 如果p2不是-1
        if (p2 != -1) {
            // 此处价值修改成p2加上本位的价值 因为p2是取了本位的物品的
            p2 = v[index] + p2;
        }
        return Integer.max(p2, p1);
    }
}
