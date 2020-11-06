package qqai.leetcode;

import java.util.*;

/**
 * @author qqai
 * @createTime 2020/9/30 09:49
 * @description：最长回文字符串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */

public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "abcba";
        System.out.println(new LongestPalindrome().longestPalindrome1(s));
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > maxLength && check(s, i, j)) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, maxLength + begin);
    }

    private boolean check(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public String longestPalindrome1(String s) {
        if (s.length() < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        // 初始化动态转移表
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        TreeSet<Object> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        for (Object o : set) {
            System.out.println(o);
        }

        // 填表得过程
        for (int j = 1; j < charArray.length; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                for (int k = 0; k < dp.length; k++) {
                    System.out.println(Arrays.toString(dp[k]));
                }

                System.out.println("======================================================");
            }
        }

        return s;
    }
}
