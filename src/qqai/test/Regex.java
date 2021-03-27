package qqai.test;

/**
 * 正则表达式 by chenaiquan 2021/03/2021/3/18 19.45
 */
public class Regex {
  public static void main(String[] args) {
    String s = "12345623217890123";
    System.out.println(s.matches("^\\d{13}$"));
  }
}
