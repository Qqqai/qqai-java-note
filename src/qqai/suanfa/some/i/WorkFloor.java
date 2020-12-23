package qqai.suanfa.some.i;

/**
 * 走楼梯
 *
 * @author qqai
 * @createTime 2020/11/11 22:00
 */

public class WorkFloor {
    public static void main(String[] args) {
        System.out.println(new WorkFloor().recursion(10));
    }

    private int recursion(int i) {
        if (i <= 0) return 0;
        if (i == 1) return 1;
        if (i == 2) return 2;
        return recursion(i - 1) + recursion(i - 2);
    }
}
