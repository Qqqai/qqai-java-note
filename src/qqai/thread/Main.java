package qqai.thread;

import java.util.concurrent.*;

/**
 * 线程
 *
 * @author qqai
 * @createTime 2020/12/30 22:47
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //
        new Thread(() -> {
            System.out.println("thread...");
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable...");
            }
        }).start();

        new Thread(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("callable");
                return 1;
            }
        })).start();

        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(20);
        Executors.newScheduledThreadPool(20);
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool();

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("interrupt fail....");
                e.printStackTrace();
            }
        });
        // 线程启动会在方法执行10000秒
        thread.start();
        // stop强制终止  不推荐
        thread.stop();
        // 中断前
        System.out.println(thread.getName() + "----->");
        // 阻塞一下主线程
        Thread.sleep(5000);
        // 线程中断
        thread.interrupt();
        // 中断后
        System.out.println(thread.getName() + "--------<");
        // 阻塞一下主线程
        Thread.sleep(3000);
    }
}
