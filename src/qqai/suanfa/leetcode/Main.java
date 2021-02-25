package qqai.suanfa.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 描述：峰值
 *
 * @author qqai
 * @createTime 2020-09-16 17:55
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = scanner.nextInt();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = scanner.nextInt();
        }
        for (int i = 0; i < ints.length; i++) {
            map.put(ints[i], i);
        }
        Arrays.sort(ints);
        int max = ints[ints.length - 1];
        System.out.println(map.get(max));
    }
}
