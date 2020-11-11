package qqai.leetcode;

/**
 * @author qqai
 * @createTime 2020/11/11 22:00
 * @description：走楼梯
 */

// TODO 这题我不会
public class WorkFloor {
    public static void main(String[] args) {
        System.out.println(new WorkFloor().recursion(7));
    }

    private int recursion(int i) {
        if (i < 0) return 0;
        if (i == 1 || i == 0) return 1;
        if (i == 2) return 2;
        return recursion(i - 1) + recursion(i - 2) + recursion(i - 3);
    }
}
