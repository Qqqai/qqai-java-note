package qqai.heimatest;

import java.util.Scanner;

/**
 * 描述：二进制判断位数区别
 *
 * @author qqai
 * @createTime 2020-09-24 9:57
 */

public class ErJinZhi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();
        int count = 0;
        int tmp1;
        int tmp2;
        while (true) {
            if (i == 0 && j == 0) {
                break;
            }
            tmp1 = i & 1;
            tmp2 = j & 1;
            if (tmp1 != tmp2) {
                count++;
            }
            i = i >>> 1;
            j = j >>> 1;
        }
        System.out.println(count);
    }
}
