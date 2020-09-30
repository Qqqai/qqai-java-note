package qqai.mianshi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/9/28 18:59
 * @description：
 */

public class ShenZhouMianShi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        int[] a = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            a[i] = Integer.parseInt(split[i]);
        }
        int count = sortDIGui(a, 0);
        System.out.println(count);
    }

    private static int sortDIGui(int[] a, int count) {
        int temp = 0;
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                temp = a[i];
                j = i;
                flag = true;
                break;
            }
        }
        if (!flag) {
            return count;
        }
        if (flag) {
            for (int i = j; i < a.length - 1; i++) {
                a[i] = a[i + 1];
            }
            a[a.length - 1] = temp;
            count++;
        }
        return sortDIGui(a, count);
    }
}
