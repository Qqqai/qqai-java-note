package qqai.java.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/11/3 14:11
 * @description：string类型的重点偏点
 */

public class TestString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "中大大阿萨啊";
        // 指定获取字节码的编码方式
        System.out.println(Arrays.toString(str.getBytes(StandardCharsets.ISO_8859_1)));
        // [63, 63, 63, 63, 63, 63]
        System.out.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)));
        // [-28, -72, -83, -27, -92, -89, -27, -92, -89, -23, -104, -65, -24, -112, -88, -27, -107, -118]
        String temp = new String(str.getBytes(StandardCharsets.UTF_8));
        // 删除字符串的前导或后置空格
        temp = temp.trim();
        System.out.println(temp);
        // 中大大阿萨啊

        int a = -324;
        String s = String.valueOf(a);
        System.out.println(s);
        String s1 = new String("1");
        String s3 = s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s1 == s3);


        String utf8 = "2020\u6D51\u5669\u4E0D\u582A\uFF0C\u7834\u91DC\u6C89\u821F\uFF0C\u6709\u5E78\u9047\u4F60\u3002\n2021\u594B\u529B\u51FA\u51FB\uFF0C\u4ECE\u4ECA\u5F80\u540E\uFF0C\u4F46\u613F\u6709\u4F60\u3002";
        System.out.println(URLDecoder.decode(utf8, StandardCharsets.UTF_8));
    }
}
