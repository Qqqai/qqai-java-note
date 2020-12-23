package qqai.suanfa.jinjie.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的所有序列
 *
 * @author qqai
 * @createTime 2020/12/15 15:44
 */
public class PrintAllSubsequence {
    public static void main(String[] args) {
        String test = "abc";
        List<String> list = printAllSubsequence(test, 0, "", new ArrayList<>());
        System.out.println(list);
    }

    /**
     * 获取全部序列
     *
     * @param str  源字符串
     * @param i    当前位置 str的索引
     * @param res  上一步组成的子串的内容
     * @param list 结果集
     * @return 结果集
     */
    private static List<String> printAllSubsequence(String str, int i, String res, List<String> list) {
        // i推进到字符串的最后一个位置 直接打印
        if (i == str.length()) {
            list.add(res);
            return list;
        }
        // 每个位置都有两种情况  一种是带上本位字符  一种是不带本为字符  就出现了两种递归
        printAllSubsequence(str, i + 1, res + str.charAt(i), list);
        printAllSubsequence(str, i + 1, res, list);
        return list;
    }
}
