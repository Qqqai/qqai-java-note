package qqai.java.base;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
    }
}
