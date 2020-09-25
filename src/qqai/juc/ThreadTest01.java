package qqai.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：测试普通线程调用方法
 *
 * @author qqai
 * @createTime 2020-09-06 21:27
 */

class Ticket {
    private int num = 60;
    private Lock lock = new ReentrantLock();

    public synchronized void saleTicket() {  //关键字方式
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "->第->" + num + "张-> 还剩下" + (--num));
        }
    }

//    Thread.State

    public synchronized void saleTicket01() {  //juc的锁方式
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "->第->" + num + "张-> 还剩下" + (--num));
            }
        } finally {
            lock.unlock();
        }
    }
}


/**
 * 笔记 线程的方法  .start();   并不是调用这个start方法就会开启这个线程，而是让这个线程进入就绪态，具体的执行需要等到cpu和操作系统底层的调度
 */
public class ThreadTest01 {
    //笔记  线程   操作  资源类
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();

        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "售货员1号").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "售货员2号").start();



        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "售货员3号").start();
    }
}
