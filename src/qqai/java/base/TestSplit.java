package qqai.java.base;

import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url截取 by ai q 2021/3/5 22:34
 */
public class TestSplit {
  public static void main(String[] args) {
    String url = "http://192.168.32.69/abc/absds/xassd/fsa.com";
    Pattern compile = Pattern.compile("(/\\w/)");
    Matcher matcher = compile.matcher(url);
    String s = matcher.group();
    System.out.println(s);
  }
}
