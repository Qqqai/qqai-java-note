package qqai.test;

import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/12/3 22:24
 * @description：解密加密
 */

public class Test04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String jiami = Encode.jiaMi(s);
        System.out.println("加密后的字符串为:" + jiami);
        String jiemi = Decode.jieMi(jiami);
        System.out.println("解密后的字符串为:" + jiemi);
    }

    static class Encode {
        private static String jiaMi(String s) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                res.append((char) (s.charAt(i) + 23));
            }
            return res.toString();
        }
    }

    static class Decode {
        private static String jieMi(String jiami) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < jiami.length(); i++) {
                res.append((char) (jiami.charAt(i) - 23));
            }
            return res.toString();
        }
    }
}
