package qqai.suanfa.jinjie.dp;

/**
 * 字符串的转换 一个字符串1对应A 2对应B 3对应C...
 * 111  -> AAA or KA or AK
 * 给定一个纯数字的字符串str能有多少种转换结果
 *
 * @author qqai
 * @createTime 2020/12/23 16:46
 */
public class ConvertString {

    public static void main(String[] args) {
        String str = "13231232432";
        System.out.println(process(str.toCharArray(), 0));
    }

    /**
     * 暴力递归  尝试模型
     * i 位置之前的内容已经经过转换了  所以不用关心
     * 这个相当于求取i之后有多少种转换结果
     *
     * @param str
     * @param i
     * @return
     */
    public static int process(char[] str, int i) {
        // base case i 来到终止位置了 可能是没有字符了 也可能是0~i-1上找到了一种转换
        if (i == str.length) {
            return 1;
        }
        // 不能转换0 所以这里直接返回0 表示以前的转换出了问题 让i位置独自面对了0
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            // i独自成为一个转换点
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                // i和i+1一起成为一个转换点
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            // i独自成为一个转换点
            int res = process(str, i + 1);
            // 只能到26所以 要判断i+1位置是否越界
            if (i + 1 < str.length && str[i + 1] <= '6') {
                // i和i+1一起成为一个转换点
                res += process(str, i + 2);
            }
            return res;
        }
        // i in 3~9 3到9就不能在后面拼数字组成新的字母 所以3~9之间的数字只能是独自成位转换点 直接递归i + 1
        return process(str, i + 1);
    }
}
