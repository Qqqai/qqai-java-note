package qqai.threadlocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/11/5 20:33
 * @description：弱引用
 */

public class ThreadLocal02 {
    public static void main(String[] args) {
        // 创建一个强引用对象指向new WeakReference<>(new M());   包含一个弱引用对象指向new M()
        WeakReference<M> reference = new WeakReference<>(new M());
        // 打印
        System.out.println(reference.get());
        // GC
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 标记 在获取 这里弱引用会被回收  这个上一步就打印了M对象被回收的方法
        System.out.println(reference.get());
    }
}
