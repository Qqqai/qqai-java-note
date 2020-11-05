package qqai.threadlocal;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/5 20:24
 * @description：
 */

public class ThreadLocal01 {

    public static void main(String[] args) {
        // new SoftReference<>这是强引用 这个包含一个软引用指向了new byte[1024 * 1024 * 10]  jvm会特殊处理这个软引用
        SoftReference<byte[]> reference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        // 获取软引用
        System.out.println(reference.get());
        // GC
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这里就能获取到这个引用，所以垃圾回收不会回收到这个软引用
        System.out.println(reference.get());

        // 给jvm一个参数 -Xmx=20  jvm会占据一部分内存  这是在创建数组就没有足够的内存了  所以这个时候会回收软引用
        byte[] b = new byte[1024 * 1024 * 12];
        // b有了
        System.out.println(b);
        // 标记 这个软引用被回收了！！
        System.out.println(reference.get());
    }
}
