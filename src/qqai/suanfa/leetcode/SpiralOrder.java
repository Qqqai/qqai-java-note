package qqai.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54 by chenaiquan 2021/03/2021/3/15 10.06
 */
public class SpiralOrder {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> order = new ArrayList<Integer>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return order;
    }
    int rows = matrix.length, columns = matrix[0].length;
    int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
    while (left <= right && top <= bottom) {
      for (int column = left; column <= right; column++) {
        order.add(matrix[top][column]);
      }
      for (int row = top + 1; row <= bottom; row++) {
        order.add(matrix[row][right]);
      }
      if (left < right && top < bottom) {
        for (int column = right - 1; column > left; column--) {
          order.add(matrix[bottom][column]);
        }
        for (int row = bottom; row > top; row--) {
          order.add(matrix[row][left]);
        }
      }
      left++;
      right--;
      top++;
      bottom--;
    }
    return order;
  }
}