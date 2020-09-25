package qqai.heimatest;

import java.util.Scanner;

/**
 * 描述：聪明的编辑
 *
 * @author qqai
 * @createTime 2020-09-24 10:58
 */

public class CongMingDeBianJi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.next();
        }
        for (int i = 0; i < n; i++) {
            String res = fun(s[i]);
            System.out.println(res);
        }
    }

    private static String fun(String next) {
        char[] c = next.toCharArray();
        if (c.length < 4) {
            return next;
        }
        for (int i = 0; i < c.length; i++) {
            if (i + 2 >= c.length) {
                break;
            }
            if (c[i] == c[i + 1] && c[i + 1] == c[i + 2]) {
                c[i + 2] = ' ';
                break;
            }
            if (i + 3 >= c.length) {
                break;
            }
            if (c[i] == c[i + 1] && c[i + 2] == c[i + 3]) {
                c[i + 3] = ' ';
                break;
            }
        }
        String s = String.valueOf(c);
        String res = s;
        if (s.contains(" ")) {
            res = s.replace(" ", "");
            return fun(res);
        } else {
            res = s.replace(" ", "");
            return res;
        }
    }
}
