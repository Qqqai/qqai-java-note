package qqai.bishi.langchao;

import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/21 22:30
 * @description：浪潮 时间限制： 3000MS
 * 内存限制： 655360KB
 * 题目描述：
 * 某条街道两侧分别种植了一排树木，并按如下编号：
 * 1 3 5 7 .... 45 47 49 ... 99
 * 2 4 6 8 ... 46 48 50 ... 100
 * 但是有一些树被砍去，希望你能找出一边最长的连续的大树。
 * 输入描述
 * 第一行一个整数N
 * 第二行N个整数表示被砍去树的编号
 * 输出描述
 * M 和 X（表示从第M棵大树开始，共有连续的X棵大树，如果有多个解，输出M最小的解即可）
 * 样例输入
 * 5
 * 9 15 27 35 6
 * 样例输出
 * 8 47
 */
public class Main2 {
    public static void main(String[] args) {
        int[] a = new int[51];
        int[] b = new int[51];
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < 101; i++) {
            if (i != 0) {
                if (i % 2 == 0) {
                    a[temp1++] = i;
                } else {
                    b[temp2++] = i;
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int index = scanner.nextInt();
            if (index % 2 == 0) {
                for (int j = 0; j < a.length; j++) {
                    if (a[j] == index) {
                        a[j] = 0;
                    }
                }
            } else {
                for (int j = 0; j < b.length; j++) {
                    if (b[j] == index) {
                        b[j] = 0;
                    }
                }
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i : a) {
            if (i == 0) {
                s.append("r").append(i).append(",");
            } else {
                s.append(i).append(",");
            }
        }
        String[] split = s.toString().split("r0,");
        String res = "";
        for (String str : split) {
            if (str.length() > res.length()) {
                res = str;
            }
        }
        StringBuilder f = new StringBuilder();
        for (int i : b) {
            f.append(i).append(",");
        }
        String[] splitb = f.toString().split(",0,");
        String resB = "";
        for (String str : splitb) {
            if (str.length() > resB.length()) {
                resB = str;
            }
        }
        if (res.split(",").length > resB.split(",").length) {
            System.out.println(res.split(",")[0] + " " + res.split(",").length);
        } else {
            System.out.println(resB.split(",")[0] + " " + resB.split(",").length);
        }
    }
}

/**
 * int start = 0;
 * int indexTemp = 0;
 * int length = 0;
 * for (int i = 0; i < a.length; i++) {
 * if (i == a.length - 1) {
 * if (i - indexTemp - 1 > length) {
 * start = indexTemp;
 * length = i - start;
 * break;
 * }
 * }
 * if (a[i] == 0) {
 * if (i - indexTemp - 1 > length) {
 * start = indexTemp;
 * length = i - start;
 * }
 * indexTemp = i;
 * }
 * }
 * <p>
 * int startb = 0;
 * int indexTempb = 0;
 * int lengthb = 0;
 * for (int i = 0; i < b.length; i++) {
 * if (i == a.length - 1) {
 * if (i - indexTempb - 1 > lengthb) {
 * startb = indexTempb;
 * lengthb = i - startb;
 * break;
 * }
 * }
 * if (b[i] == 0) {
 * if (i - indexTempb - 1 > lengthb) {
 * startb = indexTempb;
 * lengthb = i - startb;
 * }
 * indexTempb = i;
 * }
 * }
 * <p>
 * if (lengthb < length) {
 * System.out.println(a[start + 1] + " " + length);
 * } else {
 * System.out.println(b[startb + 1] + " " + lengthb);
 * }
 */