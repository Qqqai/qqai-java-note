package qqai.heimatest;

import java.util.Scanner;

/**
 * 描述：01串
 *
 * @author qqai
 * @createTime 2020-09-24 10:15
 */

public class LingYiChuan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String res = zuichangzichuan(s);
        System.out.println(res.length());
    }

    private static String zuichangzichuan(String s) {
        String m = "01";
        String n = "10";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.contains(m)) {
                if (res.length() < m.length()) {
                    res = m;
                }
                if (m.length() % 2 == 0) {
                    m += "0";
                } else {
                    m += "1";
                }
            }
            if (s.contains(n)) {
                if (res.length() < n.length()) {
                    res = n;
                }
                if (n.length() % 2 == 0) {
                    n += "1";
                } else {
                    n += "0";
                }
            }
        }
        return res;
    }
}
