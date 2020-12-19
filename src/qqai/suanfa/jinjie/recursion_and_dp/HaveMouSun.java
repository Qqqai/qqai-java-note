package qqai.suanfa.jinjie.recursion_and_dp;

/**
 * 母牛生子问题  第0年有1只母牛 第1年有两只 每只牛需要三年成熟才能生子 所以第2年是3只 第3年是4只
 * 第四年是6只 第五年是9只 第六年是13只
 * 1，2，3，4，6，9，13  斐波那契额数列
 *
 * @author qqai
 * @createTime 2020/12/15 17:30
 */
public class HaveMouSun {
    public static void main(String[] args) {
        System.out.println(getNum(4));
    }

    private static int getNum(int year) {
        if (year == 1) return 2;
        if (year == 2) return 3;
        if (year == 3) return 4;
        return getNum(year - 1) + getNum(year - 3);
    }
}
