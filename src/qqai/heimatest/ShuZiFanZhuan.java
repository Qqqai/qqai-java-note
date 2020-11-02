package qqai.heimatest;

import java.util.Scanner;

/**
 * 描述：数字反转
 *
 * @author qqai
 * @createTime 2020-09-24 9:33
 */

public class ShuZiFanZhuan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();
        System.out.println(rev((rev(i) + rev(j))));
    }

    private static int rev(int x) {
        StringBuffer buffer = new StringBuffer(String.valueOf(x));
        StringBuffer reverse = buffer.reverse();
        int i = Integer.parseInt(reverse.toString());
        return i;
    }
}
