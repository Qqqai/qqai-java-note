package qqai.thredpool;

import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/11/22 15:45
 * @description：测试线程
 */

public class TestThread extends Thread {
    @Override
    public void run() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getId());
        }).start();
        System.out.println(Thread.currentThread().getId());

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        System.out.println(group.activeCount());
    }

    public static void main(String[] args) {
        new TestThread().start();
    }
}
