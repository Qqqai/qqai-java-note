package qqai.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * by qqai
 * 2021/1/29 13:40
 */
public class Test12 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Set<Integer> set = new HashSet<>();
//        System.out.println(set.add(1));
//        System.out.println(set.add(1));
//        System.out.println(set.add(2));
        StringBuilder utfcode = new StringBuilder();
        for (byte bit : "二狗最美".getBytes(StandardCharsets.UTF_8)) {
            char hex = (char) (bit & 0xFF);
            utfcode.append(Integer.toHexString(hex));
        }
        System.out.println(utfcode.toString());
    }
}
