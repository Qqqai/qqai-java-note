package qqai.suanfa.jinjie.dp;

import java.util.Stack;

/**
 * 逆序一个栈  不能使用其他的数据结构
 *
 * @author qqai
 * @createTime 2020/12/23 11:52
 */
public class ReceiveStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        // 逆序
        receiveStack(stack);
        // 打印
        stack.forEach(System.out::println);
    }

    /**
     * 递归开始
     *
     * @param stack source stack
     */
    private static void receiveStack(Stack<Integer> stack) {
        // stack 为空 返回
        if (stack.isEmpty()) return;
        // 获得到最后一元素
        int i = f(stack);
        receiveStack(stack);
        stack.push(i);
    }

    /**
     * 获得到当前栈的最后一个节点
     *
     * @param stack source stack
     * @return last num
     */
    private static int f(Stack<Integer> stack) {
        // 弹出当前栈顶
        Integer result = stack.pop();
        // 栈为空的话栈顶就是栈底 返回result就好了
        if (stack.isEmpty()) {
            return result;
        } else {
            // 递归获取到最后一个元素
            int last = f(stack);
            // 把栈顶在推到栈中  递归完成后如下
            /* 笔记
     stack ->  | 1 |            last = 3
               | 2 | --> | 1 |
               | 3 |     | 2 |
             */
            stack.push(result);
            return last;
        }
    }
}
