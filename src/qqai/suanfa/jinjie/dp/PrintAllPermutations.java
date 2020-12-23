package qqai.suanfa.jinjie.dp;

import qqai.suanfa.common.Swap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印一个字符串的全排列
 *
 * @author qqai
 * @createTime 2020/12/15 15:59
 */
public class PrintAllPermutations {

    private static final HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        String test = "abc";
//        printAllPermutations(test.toCharArray(), 0);
//        System.out.println(set);

        List<String> list = printAllPermutations2(test.toCharArray(), 0, new ArrayList<>());
        System.out.println(list);
    }

    /**
     * 计划搜索
     *
     * @param ste
     * @param i
     */
    public static List<String> printAllPermutations2(char[] str, int i, List<String> list) {
        if (i == str.length) {
            list.add(String.valueOf(str));
            return list;
        }
        boolean[] dp = new boolean[26];
        // 标记 从i位置开始
        for (int j = i; j < str.length; j++) {
            // 这里先判断这个位置有没有被搜索过 如果搜索过了 就在重新搜索
            if (!dp[str[j] - 'a']) {
                dp[str[j] - 'a'] = true;
                Swap.swap(str, i, j);
                printAllPermutations2(str, i + 1, list);
                Swap.swap(str, i, j);
            }
        }
        return list;
    }

    /**
     * 暴力递归
     *
     * @param str
     * @param i
     */
    private static void printAllPermutations(char[] str, int i) {
        if (i == str.length) {
            set.add(String.valueOf(str));
            return;
        }
        // 遍历
        for (int j = i; j < str.length; j++) {
            // 交换位置
            Swap.swap(str, i, j);
            printAllPermutations(str, i + 1);
            // 恢复现场 在交换回来 保证每次都是同一个字符串  不然可能会出现结果比预期值少的情况
            Swap.swap(str, i, j);
        }
    }
}
