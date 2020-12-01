package qqai.shujujiegou.lianbiaohezhan;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/29 14:00
 * @description：
 */

public class P111_15 {
    public static void main(String[] args) {
        int m = 1, n = 2;
        int res = g(m, n);
        System.out.println(res);
    }

    private static int g(int m, int n) {
        if (m == 0) {
            return 0;
        }
        return g(m - 1, 2 * n) + n;
    }
}
