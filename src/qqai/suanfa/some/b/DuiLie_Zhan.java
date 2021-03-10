package qqai.suanfa.some.b;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qqai
 * @createTime 2020/12/1 20:18
 * @description：两个队列成栈
 */

public class DuiLie_Zhan {
  private Queue<Integer> data;
  private Queue<Integer> help;

  public DuiLie_Zhan() {
    this.data = new LinkedList<>();
    this.help = new LinkedList<>();
  }

  public void push(int e) {
    data.add(e);
  }

  public int pop() {
    if (data.isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    // 把data队列除最后进入的数据外全部添加到help队列里
    while (data.size() != 1) {
      help.add(data.poll());
    }
    // data队列还剩最后一个数据就是栈需要的出度
    Integer res = data.poll();
    // 交换data和help的引用地址
    swap();
    return res;
  }

  public int peek() {
    if (data.isEmpty()) {
      throw new RuntimeException("stack is empty");
    }
    while (data.size() != 1) {
      help.add(data.poll());
    }
    Integer res = data.poll();
    // 和出栈操作不一样 这个不需要抹除栈顶的元素 所以help队列把data中的最后进入的元素再加进去
    help.add(res);
    swap();
    return res;
  }

  /*标记 交换data和help的引用地址*/
  private void swap() {
    Queue<Integer> temp = data;
    data = help;
    help = temp;
  }

  public static void main(String[] args) {
    MyStack<Integer> zhan = new MyStack<>();
    zhan.push(1);
    zhan.push(2);
    zhan.push(3);
    zhan.push(4);
    System.out.println(zhan.peek());
    System.out.println(zhan.pop());
    System.out.println(zhan.peek());
    System.out.println(zhan.pop());
    System.out.println(zhan.pop());
    System.out.println(zhan.pop());
  }
}
