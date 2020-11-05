package qqai.leetcode;

/**
 * @author qqai
 * @createTime 2020/11/5 16:23
 * @description：
 */

public class Reverse {
    public int reverse(int x) {
//        String s = String.valueOf(x);
//        StringBuilder builder = new StringBuilder(s);
//        StringBuilder reverse = builder.reverse();
//        if (s.charAt(0) == '-') {
//            String[] split = reverse.toString().split("-");
//
//            return Integer.parseInt("-" + split[0].trim());
//        }
//        return Integer.parseInt(reverse.toString().trim());

        StringBuilder s = new StringBuilder(String.valueOf(Math.max(Math.abs(x), 0)));
        s.reverse();
        if (x < 0) {
            s.insert(0, '-');
        }
        try {
            return Integer.parseInt(s.toString());
        } catch (Exception e) {
            return 0;
        }
//        long l = Long.parseLong(s.toString());
//        return l < Integer.MIN_VALUE || l > Integer.MAX_VALUE ? 0 : (int)l;
    }

    public static void main(String[] args) {
        System.out.println(new Reverse().reverse(1534236469));
    }
}
