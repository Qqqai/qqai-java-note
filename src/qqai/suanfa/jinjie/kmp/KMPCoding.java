package qqai.suanfa.jinjie.kmp;

/**
 * @author qqai
 * @createTime 2020/12/8 17:03
 * @description：my kmp
 */

public class KMPCoding {
    public static int kmp(String f, String s) {
        if (f.isEmpty() || s.isEmpty()) {
            return 0;
        }
        String temp = "";
        temp = f.length() > s.length() ? s : f;
        f = f.length() > s.length() ? f : s;
        s = temp;
        int[] next = KMPCoding.getNextArray(s);
        int i = 0;
        int j = 0;
        while (i < f.length() && j < s.length()) {
            if (s.charAt(i) == f.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == s.length() ? i - j : -1;
    }

    private static int[] getNextArray(String s) {
        if (s.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[s.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(cur)) {
                next[i] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[i] = 0;
            }
        }
        return next;
    }
}
