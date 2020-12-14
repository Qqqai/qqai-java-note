package qqai.bishi.sanqihuyu;

/**
 * @author qqai
 * @createTime 2020/10/29 18:21
 * @description：
 */

public class Main {
    public static void main(String[] args) {
        int level = 5;
        System.out.println(getNum(level));
    }

    private static int getNum(int level) {
        int count = 1;
        while (level != 1) {
            if (count % 2 == 0) {
                count = count / 2 * 5;
            } else {
                count--;
                count = count / 2 * 5 + 3;
            }
            level--;
        }
        return count;
    }
}
