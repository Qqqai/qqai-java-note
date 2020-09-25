package qqai.juc;

/**
 * 描述：Lock锁
 *
 * @author qqai
 * @createTime 2020-09-07 18:54
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间按顶宇调用，实现A->B->C
 * 三个线程启动，要求如下:
 * AA打印5次，BB打丁10次，CC打15次
 * 接着
 * AA打印5次，BB打丁10次，CC打15次
 * ......来10轮
 */
public class LockTest02 {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void sout5() {
        lock.lock();
        try {
            while (num != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            }
            //通知
            num = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void sout10() {
        lock.lock();
        try {
            while (num != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            }
            //通知
            num = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void sout15() {
        lock.lock();
        try {
            while (num != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            }
            //通知
            num = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}

class ShareResource {
    public static void main(String[] args) {
        LockTest02 test02 = new LockTest02();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test02.sout5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test02.sout10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test02.sout15();
            }
        }, "C").start();
    }
}

