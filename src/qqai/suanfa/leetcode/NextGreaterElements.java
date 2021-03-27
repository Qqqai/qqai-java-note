package qqai.suanfa.leetcode;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 503 by ai q 2021/3/6 10:12
 */
public class NextGreaterElements {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int i = test(sc.nextInt());
    System.out.println(i);

  }

  public static int test(int n) {
    if (n == 1) {
      return 1;
    }
    return process("1", n - 1);
  }

  private static int process(String s, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return 2;
    }
    int i = process(s += "1", n - 1);
    int j = process(s += "01", n - 2);
    return i + j;
  }

  public void nextGreaterElements1(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int k = 0;
      int l = -1;
      while (k < i) {
        if (nums[k] < nums[i]) {
          l = nums[k];
          break;
        }
        k++;
      }
      k++;
      int r = -1;
      while (k < nums.length) {
        if (nums[k] < nums[i]) {
          r = nums[k];
          break;
        }
        k++;
      }
      System.out.println(l + " " + r);
    }
  }

  public int[] nextGreaterElements(int[] nums) {
    int[] result = new int[nums.length];
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    // 初始为-1
    Arrays.fill(result, -1);
    // 循环数组
    for (int i = 0; i < n * 2 - 1; i++) {
      // 单调栈   大 ----->　小
      while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
        result[stack.pop()] = nums[i % n];
      }
      stack.push(i % n);
    }
    return result;
  }
}
