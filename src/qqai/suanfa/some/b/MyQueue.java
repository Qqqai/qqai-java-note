package qqai.suanfa.some.b;

import java.util.Stack;

/**
 * 232 by ai q 2021/3/5 18:33
 */
public class MyQueue<T> {
  /**
   * Initialize your data structure here.
   */
  private final Stack<T> stackOne;
  private final Stack<T> stackTwo;

  public MyQueue() {
    stackOne = new Stack<T>();
    stackTwo = new Stack<T>();
  }

  /**
   * Push element x to the back of queue.
   */
  public void push(T x) {
    stackOne.push(x);
  }

  /**
   * Removes the element from in front of queue and returns that element.
   */
  public T poll() {
    if (stackTwo.isEmpty()) {
      swapElement();
    }
    return stackTwo.pop();
  }

  /**
   * swap element
   */
  private void swapElement() {
    while (!stackOne.isEmpty()) {
      stackTwo.push(stackOne.pop());
    }
  }

  /**
   * Get the front element.
   */
  public T peek() {
    if (stackTwo.isEmpty()) {
      swapElement();
    }
    return stackTwo.peek();
  }

  /**
   * Returns whether the queue is empty.
   */
  public boolean empty() {
    return stackOne.isEmpty() && stackTwo.isEmpty();
  }
}
