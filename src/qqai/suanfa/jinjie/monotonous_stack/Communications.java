package qqai.suanfa.jinjie.monotonous_stack;

import java.util.Stack;

/**
 * @author qqai
 * @createTime 2020/12/13 16:26
 * @description：单调栈——网易原题，环形的五座山问题
 */

public class Communications {
    public static long communication(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0L;
        }
        int size = arr.length;
        int maxIndex = 0;
        // 找到数组最大值的下标
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        // 得到环形数组的下一个下标
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        // 栈保存值和出现的次数
        stack.push(new Pair(value));
        // 当遍历完成就是index == maxIndex
        while (index != maxIndex) {
            // 获得当前的下标位置的值
            value = arr[index];
            // 栈为空  或者当前值小于栈顶的值不做任何操作直接入栈
            while (!stack.isEmpty() && stack.peek().value < value) {
                // 计算弹出值可以形成的对的数量
                int time = stack.pop().time;
                // getInternalSum(time) CK↓2↑  time 1
                res += getInternalSum(time) + time;
                // time 2
                res += stack.isEmpty() ? 0 : time;
                // 以上相当于  res += getInternalSum(time) + 2 * time;
            }
            // 入栈  如果栈顶和当前值相等那么只需要增加他出现的次数就可以了
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().time++;
            } else {
                stack.push(new Pair(value));
            }
            // 下一个位置
            index = nextIndex(size, index);
        }

        while (!stack.isEmpty()) {
            int time = stack.pop().time;
            res += getInternalSum(time);
            if (stack.size() > 1) {
                res += time;
            } else {
                // 最后一层
                res += stack.peek().time > 1 ? time : 0;
            }
        }
        return res;
    }

    private static long getInternalSum(int n) {
        //  (long) n * (long) (n - 1) / 2L   == CK↓2↑
        return n == 0L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    private static int nextIndex(int size, int i) {
        // 把数组变成环形数组  i到达数组的最大下表时把下一个下标换成0  从头开始
        return i < (size - 1) ? (i + 1) : 0;
    }

    private static class Pair {
        public int value;
        public int time;

        public Pair(int value) {
            this.value = value;
            this.time = 1;
        }
    }
}
