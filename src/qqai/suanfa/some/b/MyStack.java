package qqai.suanfa.some.b;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 栈 by ai q 2021/3/5 18:44
 */
public class MyStack<T> {
  private Queue<T> data;
  private Queue<T> help;

  /**
   * Initialize your data structure here.
   */
  public MyStack() {
    this.data = new LinkedList<>();
    this.help = new LinkedList<>();
  }

  /**
   * Push element x to the back of stack.
   */
  public void push(T x) {
    this.help.add(x);
    while (!data.isEmpty()) {
      help.add(data.poll());
    }
    swapReference();
  }

  /**
   * Removes the element from in front of queue and returns that element.
   */
  public T pop() {
    return data.poll();
  }

  /**
   * Get the front element.
   */
  public T peek() {
    return data.peek();
  }

  /**
   * Returns whether the queue is empty.
   */
  public boolean empty() {
    return data.isEmpty();
  }

  /**
   * swap element queue
   */
  private void swapReference() {
    Queue<T> temp = this.help;
    this.help = this.data;
    this.data = temp;
  }

}
