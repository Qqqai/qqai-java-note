package qqai.test;

import java.util.ArrayList;

/**
 * 7或11整除  但不能同时整除 by ai q 2021/3/9 13:50
 */
public class Test13 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    int i = 0;
    int j = 1000 / 7;
    while (i <= j) {
      int m = i * 7;
      int n = i * 11;
      if (m != n) {
        if (m < 1000) {
          list.add(m);
        }
        if (n < 1000) {
          list.add(n);
        }
      }
      i++;
    }
  }
}
