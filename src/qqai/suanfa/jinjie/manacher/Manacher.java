package qqai.suanfa.jinjie.manacher;

/**
 * @author qqai
 * @createTime 2020/12/6 21:28
 * @description：最长的回文子串
 */

public class Manacher {

    public static int manacher(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char[] charArr = manacherString(s);
        int[] parr = new int[charArr.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < charArr.length; i++) {
            // 会的回文瓶颈  2 * C - i = i`
            parr[i] = R > i ? Math.min(parr[2 * C - i], R - i) : 1;
            while (i + parr[i] < charArr.length && i - parr[i] > -1) {
                if (charArr[i + parr[i]] == charArr[i - parr[i]])
                    parr[i]++;
                else
                    break;
            }
            if (i + parr[i] > R) {
                R = i + parr[i];
                C = i;
            }
            max = Math.max(max, parr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String s) {
        char[] charArray = s.toCharArray();
        char[] res = new char[charArray.length * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }
        return res;
    }
}
