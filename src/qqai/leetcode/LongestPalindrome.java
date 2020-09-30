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
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = sc.nextInt();
        int sum = 0;
        for (int i = count; i > 0; i--) {
            sum += num * Math.pow(10, count - i) * i;
        }
        System.out.println(sum);
    }

    public String longestPalindrome(String s) {
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        List<Character> res = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.contains(s.charAt(i))) {
                int temp = 0;
                while (stack.size() > 0) {
                    if (s.charAt(temp + i) != stack.pop()) {
                        stack.clear();
                        break;
                    }
                    list.add(s.charAt(temp));
                    temp++;
                }
                if (list.size() > res.size()) {
                    res = list;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return list.toString();
    }
}
