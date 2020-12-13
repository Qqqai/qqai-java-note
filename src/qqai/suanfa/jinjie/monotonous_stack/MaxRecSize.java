package qqai.suanfa.jinjie.monotonous_stack;

import java.util.Stack;

/**
 * @author qqai
 * @createTime 2020/12/9 22:58
 * @description：单调栈——矩阵的最大面积 大根堆是一种解法
 */

public class MaxRecSize {

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                /*
                1 0 1 1
                1 1 1 1
                1 1 1 0

                i = 0
                height = [1,0,1,1]
                i = 1
                height = [2,1,2,2]
                i = 2
                height = [3,2,3,0]
                */
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            // 计算当前只到map[i]情况下的最大面积
            maxArea = Math.max(maxArea, maxRecFromBottom(height));
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 当前的值必须大于栈顶的值才能进栈  否则出栈 进行面积运算
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int j = stack.pop();
                // 判断栈里面还有没有东西 就拿到这个值 表示可以当前面积可以到达的位置
                int k = stack.isEmpty() ? -1 : stack.peek();
                // 可以延申的范围i - k - 1
                int curArea = (i - k - 1) * arr[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        // 数组判断完了  但是栈没有出来完就需要全部出栈结算
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (arr.length - k - 1) * arr[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
