package qqai.suanfa.leetcode;

/**
 * @author qqai
 * @createTime 2020/11/5 16:40
 * @description：回文数
 */
//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1:
//
// 输入: 121
//输出: true
//
//
// 示例 2:
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3:
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 进阶:
//
// 你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学
// 👍 1293 👎 0
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        StringBuffer s = new StringBuffer(String.valueOf(x));
        String str = s.toString();
        s.reverse();
        return str.equals(s.toString());
    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(10));
    }
}
