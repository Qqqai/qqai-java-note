package qqai.suanfa.some.b;

import java.util.Stack;

/**
 * @author qqai
 * @createTime 2020/12/1 20:46
 * @description：两个栈结构实现队列
 */

public class Zhan_DuiLie {
    private Stack<Integer> push;
    private Stack<Integer> pop;

    public Zhan_DuiLie() {
        push = new Stack<>();
        pop = new Stack<>();
    }

    // 笔记 添加元素
    public void add(int e) {
        // 首先把push中的数据全部入栈到pop中
        while (push.size() > 0) {
            pop.push(push.pop());
        }
        // 然后pop添加这个元素
        pop.push(e);
        // 在后就把pop队列中的元素全部添加到push队列中，然后出队的时候就push出栈，这样就满足了队列的特点先进先出
        while (pop.size() > 0) {
            push.push(pop.pop());
        }
    }

    public int peek() {
        if (push.isEmpty()) throw new RuntimeException("queue is empty");
        return push.peek();
    }

    public int poll() {
        return push.pop();
    }

    /*标记 交换引用*/
    private void swap() {
        Stack<Integer> temp = this.push;
        this.push = this.pop;
        this.pop = temp;
    }

    public static void main(String[] args) {
        Zhan_DuiLie duiLie = new Zhan_DuiLie();
        duiLie.add(1);
        duiLie.add(2);
        duiLie.add(3);
        duiLie.add(4);
        duiLie.add(5);
        duiLie.add(6);

        System.out.println(duiLie.peek());
        System.out.println(duiLie.peek());
        System.out.println(duiLie.poll());
        System.out.println(duiLie.poll());
        System.out.println(duiLie.poll());
    }
}
