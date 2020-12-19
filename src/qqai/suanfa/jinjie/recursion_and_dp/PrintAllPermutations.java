package qqai.suanfa.jinjie.recursion_and_dp;

import java.util.HashSet;

/**
 * 打印一个字符串的全排列
 *
 * @author qqai
 * @createTime 2020/12/15 15:59
 */
public class PrintAllPermutations {

    private static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        String test = "abc";
        printAllPermutations(test.toCharArray(), 0);
        System.out.println(set);
    }

    private static void printAllPermutations(char[] str, int i) {
        if (i == str.length) {
            set.add(String.valueOf(str));
            return;
        }
        // 遍历
        for (int j = i; j < str.length; j++) {
            // 交换位置
            swap(i, j, str);
            printAllPermutations(str, i + 1);
            // 在交换回来 保证每次都是同一个字符串  不然可能会出现结果比预期值少的情况
            swap(i, j, str);
        }
    }

    private static void swap(int i, int j, char[] str) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
