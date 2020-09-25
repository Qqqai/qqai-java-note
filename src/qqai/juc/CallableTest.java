package qqai.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 描述：Callable接口测试
 *
 * @author qqai
 * @createTime 2020-09-08 0:04
 */

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test01 test01 = new test01();
        //笔记 FutureTask这个类实现了Runnable接口，而且它的构造函数中可以包含一个Callable类型的对象；所以用这个类可以将Callable转换成Runnable类型传入Thread的构造器中进行线程的开启
        FutureTask<Integer> task = new FutureTask<>(test01);
        new Thread(task, "A").start();

        //笔记  Callable接口内置有缓存当这个线程去调用的时候会直接返回结果 不会进入方法 所以这个线程就貌似是只执行了一次，其实都执行了但是第二次直接返回了
        new Thread(task, "B").start();
        //笔记 获取线程的返回值
        Integer integer = task.get();
        System.out.println(integer);
    }

}

class test01 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " ->  come in");
        return 1024;
    }
}

class test02 implements Runnable {
    @Override
    public void run() {

    }
}