package qqai.suanfa.jinjie.recursion;

/**
 * 给定一个数组和一个整数判断矩阵是否有值相加得到这个整数
 *
 * @author qqai
 * @createTime 2020/12/15 18:25
 */
public class IsSum {
    /*标记 暴力递归*/
    public static boolean isSum(int[] arr, int i, int sum, int aim) {
        if (i == arr.length) {
            return sum == aim;
        }
        return isSum(arr, i + 1, sum + arr[i], aim) || isSum(arr, i + 1, sum, aim);
    }

    public static boolean isSum1(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(isSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 0, 12));
        System.out.println(isSum1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 12));
    }
}
