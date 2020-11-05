package qqai.threadlocal;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/11/5 20:33
 * @description：弱引用
 */

public class ThreadLocal04 {
    private static ThreadLocal<M> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            /*
                笔记  tl.set(?);看源码
                Thread t = Thread.currentThread();
                ThreadLocalMap map = getMap(t);
                if (map != null) {
                    map.set(this, value);
                } else {
                    createMap(t, value);
                }
                笔记  map.set(this, value);  set的时候会有一个Entry
                笔记 Entry继承自WeakReference 是一个弱引用，所以这个key是个弱引用，只要对象的强引用被回收了，这个弱引用就会被直接回收掉
                笔记 这个弱引用被回收了 key-value指向的value也是强引用，不会被回收，就会内存泄漏！
            */
            tl.set(new M().setA("qqai"));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 本线程装载 本线程才能获取
            System.out.println(tl.get());
            // 笔记 这个弱引用被回收了 key-value指向的value,如果这里不remove的时候，value就不会被释放，那么就会造成内存泄漏
            tl.remove();
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 非装在线程这里就获取不到
            System.out.println(tl.get());
        }).start();
    }
}
