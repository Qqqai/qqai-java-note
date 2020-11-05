package qqai.threadlocal;

import java.io.IOException;

/**
 * @author qqai
 * @createTime 2020/11/5 20:15
 * @description：ThreadLocal学习
 */

public class ThreadLocal00 {

    public static void main(String[] args) throws IOException {
        // 创建一个对象
        M m = new M();
        // 改变句柄的指向
        m = null;
        // 给出GC建议
        System.gc();
        System.out.println(m);
        // 阻塞一下主线程 给垃圾回收一点时间
        System.in.read();
    }
}
