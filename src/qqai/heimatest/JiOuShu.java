package qqai.heimatest;

import java.util.*;

/**
 * 描述：奇偶数
 *
 * @author qqai
 * @createTime 2020-09-24 10:37
 */

public class JiOuShu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] req = new int[n];
        for (int i = 0; i < n; i++) {
            req[i] = scanner.nextInt();
        }
        int[] res = huafenpaixu(req);
        for (int i : req) {
            System.out.print(i + " ");
        }
    }

    private static int[] huafenpaixu(int[] req) {
        List<Integer> oulist = new ArrayList<>();
        List<Integer> jilist = new ArrayList<>();
        for (int i : req) {
            if (i % 2 == 0) {
                oulist.add(i);
            } else {
                jilist.add(i);
            }
        }
        Collections.sort(jilist);
        Collections.sort(oulist);
        for (int i = 0; i < jilist.size(); i++) {
            req[i] = jilist.get(i);
        }
        for (int i = 0; i < oulist.size(); i++) {
            req[jilist.size() + i] = oulist.get(i);
        }
        return req;
    }
}
