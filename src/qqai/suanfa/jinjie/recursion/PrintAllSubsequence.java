package qqai.suanfa.jinjie.recursion;

/**
 * 打印一个字符串的所有子序列
 *
 * @author qqai
 * @createTime 2020/12/15 15:44
 */
public class PrintAllSubsequence {
    public static void main(String[] args) {
        String test = "abc";
        printAllSubsequence(test, 0, "");
    }

    private static void printAllSubsequence(String str, int i, String res) {
        // i推进到字符串的最后一个位置 直接打印
        if (i == str.length()) {
            System.out.println(res);
            return;
        }
        // 每个位置都有两种情况  一种是带上本位字符  一种是不带本为字符  就出现了两种递归
        printAllSubsequence(str, i + 1, res + str.charAt(i));
        printAllSubsequence(str, i + 1, res);
    }
}
