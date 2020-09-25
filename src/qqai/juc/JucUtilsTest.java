package qqai.juc;

import java.util.concurrent.*;

/**
 * 描述：工具类测试
 *
 * @author qqai
 * @createTime 2020-09-08 13:37
 */
public class JucUtilsTest {
    public static void main(String[] args) {
        //笔记 信号量Semaphore类  6表示 6个信号量
        Semaphore semaphore = new Semaphore(3);  //笔记 如果这里的信号量设置成1 就相当于一个synchronized，只有一个资源能被调用
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                //笔记 +占用一个信号量
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "-> 占用一个信号量");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "-> 释放了一个信号量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //笔记 释放信号量
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
