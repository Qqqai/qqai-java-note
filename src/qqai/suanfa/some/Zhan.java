package qqai.suanfa.some;

/**
 * @author qqai
 * @createTime 2020/12/1 16:36
 * @description：栈 标记 固定数组实现一个栈
 */

public class Zhan {
    private int index;
    private final Integer[] element;
    private static final int DEFAULT_SIZE = 16;

    private int minIndex;
    private final Integer[] min;

    public Zhan() {
        this.element = new Integer[DEFAULT_SIZE];
        this.min = new Integer[DEFAULT_SIZE];
    }

    public Zhan(int size) {
        if (size < 1) throw new IllegalArgumentException("The init size is less than 1");
        this.element = new Integer[size];
        this.min = new Integer[size];
    }

    public Integer peek() {
        if (index == 0) {
            return null;
        }
        return element[index - 1];
    }

    public Integer min() {
        if (minIndex == 0) throw new ArrayIndexOutOfBoundsException("The queue is empty");
        return min[minIndex - 1];
    }

    public void push(Integer t) {
        if (index == element.length)
            throw new ArrayIndexOutOfBoundsException("The queue is full");
        element[index++] = t;
        if (minIndex == 0 || min[minIndex - 1] > t) {
            min[minIndex++] = t;
        }
    }

    public Integer pop() {
        if (index == 0) throw new ArrayIndexOutOfBoundsException("The queue is empty");
        if (element[index - 1].equals(min[minIndex - 1])) {
            minIndex--;
        }
        return element[--index];
    }

    public static void main(String[] args) {
        Zhan zhan = new Zhan();
        zhan.push(1);
        zhan.push(2);
        zhan.push(3);
        System.out.println(zhan.peek());
        System.out.println(zhan.min());
        System.out.println(zhan.pop());
        System.out.println(zhan.min());
        System.out.println(zhan.pop());
    }
}
