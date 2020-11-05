package qqai.threadlocal;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/11/5 20:33
 * @description：弱引用
 */

public class ThreadLocal03 {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        // 创建一个强引用对象指向new PhantomReference<>(new M(), QUEUE);   包含一个虚引用对象指向new M()，  参数QUEUE是创建虚引用必须要一个队列
        PhantomReference<M> reference = new PhantomReference<>(new M(), QUEUE);
        // 笔记 虚引用无法获取  而且 虚引用在GC的时候会把这个虚引用对象添加到QUEUE中
        System.out.println(reference.get());// null

        /*
            笔记 堆外内存分配的对象，jvm无法直接管理 就会有一个虚引用指向这个对象的，GC的时候会直接把这个对象释放还会把这个对象指向的直接内存释放掉
        */
        // 这个会分配在操作系统管理的空间  在堆外内存
        // 笔记  分配直接内存 base = UNSAFE.allocateMemory(size);
        // 笔记 Cleaner继承自PhantomReference这个就是虚引用的清理器 会直接释放直接内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // 这个分配在jvm中  会在操作系统拷贝一份  所以这里的效率比上面的分配方式要慢  zero-copy的概念
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        new Thread(() -> {
            LIST.add(new byte[1024 * 1024]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.get());
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("-----虚引用被回收" + poll);
                }
            }
        }).start();

        System.gc();

        System.in.read();
    }
}
