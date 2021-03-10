package qqai.suanfa.leetcode;


import java.util.Arrays;
import java.util.Stack;

/**
 * 503 by ai q 2021/3/6 10:12
 */
public class NextGreaterElements {
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
