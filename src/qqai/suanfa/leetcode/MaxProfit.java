package qqai.suanfa.leetcode;

/**
 * 力扣188
 *
 * @author qqai
 * @createTime 2020/12/28 17:54
 */
public class MaxProfit {

    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(2, new int[]{2, 4, 1}));
    }

    /**
     * TODO  没写出来
     *
     * @param k      次
     * @param prices 股价数组
     * @return 利益最大值
     */
    public int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }
        return process(prices, 0, -1, k);
    }

    public int process(int[] prices, int index, int oldIndex, int rest) {
        if (index == prices.length - 1 && oldIndex != -1) return prices[oldIndex] - prices[index];
        if (index == prices.length - 1 && oldIndex == -1) return 0;
        if (rest == 1 && oldIndex == -1) return 0;
        if (rest == 1 && oldIndex != -1) return prices[oldIndex] - prices[index];
        if (rest == 0) return 0;
        int p1 = process(prices, index + 1, index, rest - 1);
        int p2 = process(prices, index + 1, oldIndex, rest);
        return Integer.max(p1, p2);
    }
}
