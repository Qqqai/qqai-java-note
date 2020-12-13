package qqai.suanfa.jinjie.kmp;

/**
 * @author qqai
 * @createTime 2020/12/6 15:15
 * @description：最长子串长度
 */

public class KMP {

    public static void main(String[] args) {
        String s1 = "abcabaabaabcacb";
        String s2 = "abaabcac";
        System.out.println(getIndexOf(s1, s2));
    }

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int[] next = getNextArray(str2);
        int i = 0;
        int j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length ? i - j : -1;
    }

    private static int[] getNextArray(char[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;
        // i表示得是遍历str2到了那个位置
        while (i < next.length) {
            /*
                a b a a b c a c
                i 首先在2位置 0 1 位置不相等且cur不大于0所以2位置0  i=2 cur=0;
                i==3  arr[2] == arr[0]  i++ ++cur next[3] = 1
                i==4  arr[3] != arr[1] cur>0 cur=next[1]==0 意思就是跳回到上一个被记录得最大长度得位置
                ...
             */
            // 如果i位置得字符和cur位置得字符相等 那么就代表当前得长度是
            if (arr[i - 1] == arr[cur]) {
                next[i++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
