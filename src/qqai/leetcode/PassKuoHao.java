package qqai.leetcode;

/**
 * 描述：有效的括号
 *
 * @author qqai
 * @createTime 2020-09-16 11:39
 */

public class PassKuoHao {
    public static void main(String[] args) {
        System.out.println(isValid("()()((()))("));
    }

    public static boolean isValid(String s) {
        if (s.length() <= 1) {
            return false;
        }
        char[] c = new char[s.length()];
        char[] a = s.toCharArray();
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '(') {
                c[count++] = ')';
            } else if (a[i] == '[') {
                c[count++] = ']';
            } else if (a[i] == '{') {
                c[count++] = '}';
            } else if (count <= 0 || c[--count] != a[i]) {
                return false;
            }
        }
        return count == 0;
    }
}
