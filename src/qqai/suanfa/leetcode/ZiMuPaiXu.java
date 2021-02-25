package qqai.suanfa.leetcode;

import java.util.*;

/**
 * 描述：字母排序
 *
 * @author qqai
 * @createTime 2020-09-17 10:48
 */

public class ZiMuPaiXu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.next();
        char[] s = sc.toCharArray();
        char[] chars = new char[26];
        for (char c : s) {
            chars[c - 96] += 1;
        }
        int MAX_VALUE = 0;
        char KEY = 'a';
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > MAX_VALUE) {
                MAX_VALUE = chars[i];
                KEY = (char) (i + 96);
            }
        }
        System.out.println(KEY);
        System.out.println(MAX_VALUE);
    }
}
