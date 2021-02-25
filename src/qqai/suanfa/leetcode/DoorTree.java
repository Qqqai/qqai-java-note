package qqai.suanfa.leetcode;

import java.util.Scanner;

/**
 * 描述：校门外的树
 *
 * @author qqai
 * @createTime 2020-09-16 17:12
 */

public class DoorTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int n = scanner.nextInt();
        int[] num = new int[l + 1];
        for (int i = 0; i < n; i++) {
            int begin = scanner.nextInt();
            int end = scanner.nextInt();
            if (begin > end) {
                int temp = begin;
                begin = end;
                end = temp;
            }
            for (int j = begin; j <= end; j++) {
                num[j] = -1;
            }
        }
        scanner.close();
        int sum = l + 1;
        for (int i : num) {
            sum += i;
        }
        System.out.println(sum);
    }
}
