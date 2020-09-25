package qqai.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：读写锁测试
 *
 * @author qqai
 * @createTime 2020-09-08 14:19
 */

public class ReadAndWriteLockTest {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("获取到 ->" + myCache.get("a" + finalI));
            }, i + "号读取线程").start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put("a" + finalI, "qqai-zz" + finalI);
                System.out.println("写入 -> ");
            }, i + "号写入线程").start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    //笔记 读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //笔记 写操作 写锁保证原子性
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-> 写入操作");
            Object put = map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "-> 写入成功");
        } finally {
            //释放锁
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-> 读取操作");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "-> 读取成功");
            return result;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
