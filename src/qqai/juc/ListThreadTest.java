package qqai.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 描述：List多线程测试
 *
 * @author qqai
 * @createTime 2020-09-07 21:57
 */

/**
 * 笔记  main方法执行之后，使用ArrayList当线程达到一定的数量的时候就可能出现异常java.util.ConcurrentModificationException，所以ArrayList是线程不安全的
 * 笔记  解决方法：  1、换成Vector类这个类是线程安全的List接口实现类（不推荐的方式）
 * 笔记  解决方法    2、使用Collections.synchronizedList(new ArrayList<>())把一个线程不安全的ArrayList转换成一个线程安全的集合
 * 笔记  解决办法    3、使用juc提供的CopyOnWriteArrayList类也能操作一个线程安全的ArrayList
 * 笔记  对于CopyOnWriteArrayList   ->   一个线程安全的变体ArrayList ，其中所有可变操作（ add ， set ，等等）通过对底层数组的最新副本实现。
 */
public class ListThreadTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> v = new Vector<>();
        List<String> syList = Collections.synchronizedList(new ArrayList<>());
        List<String> onWriteArrayList = new CopyOnWriteArrayList<>();

        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();

        Map<String, String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();

        //笔记 这个方法上加的有同步锁synchronized
        v.add("aaa");

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(8));
                System.out.println(Thread.currentThread().getName() + "->list:" + list);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 对于CopyOnWriteArrayList:
 * 读写是不一样的操作，内部是公共读，同步锁
 * 读取：
 * public E get(int index) {
 * return get(getArray(), index);
 * }
 * 写入：
 * public boolean add(E e) {
 * //juc锁
 * final ReentrantLock lock = this.lock;
 * lock.lock();
 * try {
 * //读取  获取到当前的List内容，复制一份
 * Object[] elements = getArray();
 * int len = elements.length;
 * //新的List
 * Object[] newElements = Arrays.copyOf(elements, len + 1);
 * //把要添加的内容放到Object数组的最后面
 * newElements[len] = e;
 * //更新CopyOnWriteArrayList的List   简单点说就是更新List的版本
 * setArray(newElements);
 * return true;
 * } finally {
 * //解锁
 * lock.unlock();
 * }
 * }
 */