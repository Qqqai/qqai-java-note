package qqai.suanfa.jinjie.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 字符串拼接比较字典序问题
 *
 * @author qqai
 * @createTime 2020/12/14 18:33
 */

public class LowestLexicography {
    /*自定义比较器*/
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            // str1 + str2 大于 str2 + str1  return -1
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        // 用规定的比较器比较序列
        Arrays.sort(strings, new MyComparator());
        StringBuilder res = new StringBuilder();
        for (String string : strings) {
            res.append(string);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] arr = {"b", "ba", "aaa", "cbd", "abc"};
        System.out.println(lowestString(arr));
    }
}
