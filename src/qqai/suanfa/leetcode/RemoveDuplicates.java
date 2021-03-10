package qqai.suanfa.leetcode;

/**
 * 1047 by ai q 2021/3/9 10:30
 */
public class RemoveDuplicates {
  public static void main(String[] args) {
    System.out.println(new RemoveDuplicates().removeDuplicates("abbaca"));
  }

  public String removeDuplicates(String s) {
    if (s.isEmpty()) {
      return "";
    }
    return stack(s);
  }

  /**
   * 栈结构
   *
   * @param s sources
   * @return target
   */
  private String stack(String s) {
    StringBuilder stack = new StringBuilder();
    int top = -1;
    for (int i = 0; i < s.length(); i++) {
      if (top >= 0 && s.charAt(i) == stack.charAt(top)) {
        stack.deleteCharAt(top);
        top--;
      } else {
        stack.append(s.charAt(i));
        top++;
      }
    }
    return stack.toString();
  }

  /**
   * 递归每次按照相同的字符split  获得到target字符后再次递归
   *
   * @param s sources
   * @return target
   */
  private String process(String s) {
    int i = 0;
    int j = 1;
    StringBuilder res = new StringBuilder();
    while (i < s.length() && j < s.length()) {
      if (s.charAt(i) == s.charAt(j)) {
        String[] split = s.split(s.charAt(i) + "" + s.charAt(j));
        for (String str : split) {
          res.append(str);
        }
        return process(res.toString());
      }
      i++;
      j++;
    }
    // 没有递归 说明没有相同字符相连返回元字符串
    return s;
  }
}
