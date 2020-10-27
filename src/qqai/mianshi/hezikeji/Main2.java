package qqai.mianshi.hezikeji;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/10/25 17:25
 * @description：盒子科技 要求1、字符串压缩前与字符串压缩后
 * “AAAABC2DDDDDEFFFFF” --> “A4BC2D5EF5”
 * //要求2、压缩后字符串长度如果长度大于或等于原字符串长度，输出原字符串
 * “AABBCCDDEEFF” --> “A2B2C2D2E2F2” (长度不变)
 * “AABBCCDDEEFF” --> “AABBCCDDEEFF” (压缩结果为原字符串)
 */

public class Main2 {

    public static void main(String[] args) {
        String s = "AABBCCDDEEFF";
        char[] chars = s.toCharArray();
        int first = 0;
        int next = 0;
        while (first < s.length()) {
            while (true) {
                if (next < s.length() && s.charAt(next) == s.charAt(first)) {
                    next++;
                } else {
                    break;
                }
            }
            String substring = s.substring(0, first);
            String sub = s.substring(next);
            if (next - first != 1) {
                s = substring + s.charAt(first) + "" + (next - first) + sub;
                first = first + 2;
            } else {
                first = first + 1;
            }
            next = first;
        }
        if (s.length() == chars.length) {
            for (char c : chars) {
                System.out.print(c);
            }
        } else {
            System.out.println(s);
        }
    }
}
