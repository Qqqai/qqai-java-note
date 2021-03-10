package qqai.suanfa.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 224 by ai q 2021/3/10 11:51
 */
public class Calculate {
  public static void main(String[] args) {
    System.out.println(new Calculate().calculate("(1+(4+5+2)-3)+(6+8)"));
  }

  public int calculate(String s) {
    Deque<Integer> ops = new LinkedList<Integer>();
    ops.push(1);
    int sign = 1;
    int ret = 0;
    int n = s.length();
    int i = 0;
    while (i < n) {
      if (s.charAt(i) == ' ') {
        i++;
      } else if (s.charAt(i) == '+') {
        sign = ops.peek();
        i++;
      } else if (s.charAt(i) == '-') {
        sign = -ops.peek();
        i++;
      } else if (s.charAt(i) == '(') {
        ops.push(sign);
        i++;
      } else if (s.charAt(i) == ')') {
        ops.pop();
        i++;
      } else {
        long num = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
          num = num * 10 + s.charAt(i) - '0';
          i++;
        }
        ret += sign * num;
      }
    }
    return ret;
  }
}