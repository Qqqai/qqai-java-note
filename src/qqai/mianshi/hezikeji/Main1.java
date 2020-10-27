package qqai.mianshi.hezikeji;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author qqai
 * @createTime 2020/10/25 16:51
 * @description：盒子科技
 */

public class Main1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //第一个线程任务执行
        CompletableFuture<Void> A = CompletableFuture.runAsync(() -> {
            System.out.println("A线程任务执行完成....");
        });
        //第二个线程任务执行
        CompletableFuture<Void> B = CompletableFuture.runAsync(() -> {
            System.out.println("B线程任务执行完成....");
        });
        //等待前两个线程任务都执行完成  get()方法这里是阻塞的
        CompletableFuture.allOf(A, B).get();
        //get方法放行表示前两个任务都完成了 c任务执行
        CompletableFuture<Void> C = CompletableFuture.runAsync(() -> {
            System.out.println("C线程执行完成....");
        });
    }
}
