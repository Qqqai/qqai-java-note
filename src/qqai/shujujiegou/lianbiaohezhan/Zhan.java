package qqai.shujujiegou.lianbiaohezhan;

import org.jetbrains.annotations.NotNull;

/**
 * @author qqai
 * @createTime 2020/11/28 21:44
 * @description：栈
 */

public class Zhan<T> {

    private final Node<T> head;

    private int size;

    private static final int DEFAULT_SIZE = 0;

    public Zhan() {
        head = new Node<>();
        size = DEFAULT_SIZE;
    }

    public void push(T element) {
        Node<T> node = new Node<T>();
        node.value = element;
        if (head.next != null) {
            node.next = head.next;
        }
        head.next = node;
        size++;
    }

    public T pop() {
        if (head.next == null) {
            throw new NullPointerException("栈内无元素");
        }
        Node<T> node = head.next;
        head.next = node.next;
        size--;
        return node.value;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> pre;
    }

    public static void main(String[] args) {
//        Zhan<Integer> zhan = new Zhan<>();
//        zhan.push(1);
//        zhan.push(2);
//        zhan.push(3);
//        System.out.println(zhan.pop());
//        System.out.println(zhan.pop());
//        System.out.println(zhan.pop());
//        System.out.println(zhan.pop());
        // 标记 实现括号匹配算法
        String s = "(abcd)[[[]]";
        System.out.println(isValid(s));
    }

    private static boolean isValid(@NotNull String s) {
        Zhan<Character> zhan = new Zhan<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    zhan.push(')');
                    break;
                case '[':
                    zhan.push(']');
                    break;
                case '{':
                    zhan.push('}');
                    break;
                case ')':
                    if (zhan.pop() != ')') {
                        return false;
                    }
                    break;
                case ']':
                    if (zhan.pop() != ']') {
                        return false;
                    }
                    break;
                case '}':
                    if (zhan.pop() != '}') {
                        return false;
                    }
                    break;
            }
        }
        return zhan.size() == 0;
    }
}
