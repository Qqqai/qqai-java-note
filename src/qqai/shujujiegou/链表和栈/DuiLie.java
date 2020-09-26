package qqai.shujujiegou.链表和栈;


import java.util.AbstractList;

/**
 * 描述：队列
 *
 * @author qqai
 * @createTime 2020-09-17 18:55
 */

public class DuiLie {
    public static void main(String[] args) {
//        ArrayQueue
        MyAroundQueue<Integer> queue = new MyAroundQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.remove(0));
        }
    }
}


class MyAroundQueue<T> extends AbstractList<T> {

    private int front = 0;//对头
    private int rear = 0;//对尾
    private int maxSize;//最大可用空间
    private T[] queue;

    public MyAroundQueue() {
        this.maxSize = 16;
        this.queue = this.newArray(16 + 1);
    }

    private T[] newArray(int i) {
        return (T[]) new Object[i];
    }

    @Override
    public boolean add(T t) {
        queue[rear] = t;
        int newRear = (this.rear + 1) % this.queue.length;
        if (newRear == this.front)
            throw new IndexOutOfBoundsException("队列已满~");
        this.rear = newRear;
        return true;
    }

    @Override
    public T remove(int i) {
        if (i != 0) throw new IndexOutOfBoundsException("只能从对头出栈");
        if (front == rear) throw new IndexOutOfBoundsException("队列为空");
        T t = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        return t;
    }

    public MyAroundQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        this.queue = newArray(maxSize + 1);
    }

    @Override
    public T get(int index) {
        if (front == rear) throw new IndexOutOfBoundsException("队列为空");
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("元素为空");
        }
        T t = queue[front + index];
        return t;
    }

    @Override
    public int size() {
        int i = rear - front;
        return i < 0 ? i + maxSize : i;
    }
}
