package qqai.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：Lock锁测试
 *
 * @author qqai
 * @createTime 2020-09-07 14:34
 */

public class LockTest01 {

}

class BoundedBuffer {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int num = 0;

    private void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
//                this.wait();
                //juc 替换掉原本的wait方法
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "-> " + num);
            //通知其他线程
//            this.notifyAll();
            //juc替换
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
//                this.wait();
                //juc 替换掉原本的wait方法
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "-> " + num);
            //通知其他线程
//            this.notifyAll();
            //juc替换
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}