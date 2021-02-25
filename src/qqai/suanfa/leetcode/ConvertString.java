package qqai.suanfa.leetcode;

import java.util.ArrayList;

/**
 * 6
 *
 * @author qqai
 * @createTime 2020/12/26 00:40
 */
public class ConvertString {
    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        System.out.println(convert2(s, 3));
    }

    public static String convert2(String s, int n) {
        StringBuilder res = new StringBuilder();
        if (n == 1) return s;
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new StringBuilder());
        }
        boolean down = false;
        int row = 0;
        for (char c : s.toCharArray()) {
            list.get(row).append(c);
            if (row == 0 || row == n - 1) down = !down;
            row += down ? 1 : -1;
        }
        for (StringBuilder str : list) {
            res.append(str.toString());
        }
        return res.toString();
    }

    public static String convert(String s, int n) {
        int M = s.length() - 1;
        String res = "";
        char[][] dp = new char[n][M];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < dp.length && j < dp[i].length && index <= M) {
            while (i < dp.length && index <= M) {
                dp[i][j] = s.charAt(index++);
                i++;
            }
            i--;
            index--;
            int k = j + n - 2;
            while (j <= k && index <= M) {
                dp[i][j] = s.charAt(index++);
                i--;
                j++;
            }
            if (i == -1) {
                i++;
            }
        }
        for (char[] chars : dp) {
            for (char c : chars) {
                System.out.print(c + "\t");
            }
            System.out.println();
        }
        return res;
    }


}
