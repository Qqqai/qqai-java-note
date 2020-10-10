package qqai.mianshi;

/**
 * @author qqai
 * @createTime 2020/10/10 14:53
 * @description：保融面试
 */

public class BaoRongMainShi {
    public static void main(String[] args) {
        String s = "hello world dadafagfgdsg dad as aff a fae a afdsh";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean b = (s.charAt(i) >= 97 && s.charAt(i) <= 123)
                    || (s.charAt(i) >= 65 && s.charAt(i) <= 91);
            if ((b) && i != s.length() - 1) {
                count++;
            } else if (b) {
                System.out.println(++count);
            } else if (i != s.length() - 1) {
                count = 0;
            } else {
                System.out.println(0);
            }
        }
    }
}
