package qqai.thredpool;

import java.util.concurrent.*;

/**
 * 异步任务——>线程测试
 *
 * @author qqai
 * @createTime 2020-08-05 15:49
 */
/*笔记*/
public class ThreadTest {
    // 笔记       开启一个线程池  并且付给这个线程池10个任务队列  每个异步任务直接提交给线程池执行 让他自己去执行
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");

        /**
         * 笔记 测试CompletableFuture
         */
//        CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//        }, executor);
        /**
         * 运行结果
         * main...start...
         * main...end...
         * 当前线程12
         * 运行结果5
         */


//        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
//        }, executor);
//        System.out.println("异步执行完成后的返回值" + supplyAsync.get());
        /**
         * 运行结果
         * main...start...
         * 当前线程12
         * 运行结果5
         * 异步执行完成后的返回值5
         * main...end...
         */

//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
//        }, executor).whenComplete((res, err) -> {
//            System.out.println("异步成功 回调的方法...结果是：" + res + "   异常是：" + err);
//        });
        /**
         * 执行结果
         * main...start...
         * 当前线程12
         * 运行结果5
         * 异步成功 回调的方法...结果是：5   异常是：null
         * main...end...
         */

//        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 0;
//            System.out.println("运行结果" + i);
//            return i;
//        }, executor).whenComplete((res, err) -> {
//            System.out.println("异步成功 回调的方法...结果是：" + res + "   异常是：" + err);
//        }).exceptionally(throwable -> {
//            //出现异常默认返回一个 10
//            return 10;
//        });
//        System.out.println(exceptionally.get());

        //笔记  whenComplete只能感知异常但是不能处理异常，exceptionally可以感知异常并且修改返回的结果

        /**
         * 出现异常时的回调
         * main...start...
         * 当前线程12
         * 异步成功 回调的方法...结果是：null   异常是：java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         * 10
         * main...end...
         */


//        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
//            //笔记 方法能返回结果 还能感知异常修改结果
//        }, executor).handle((integer, throwable) -> {
//            if (integer != null) {
//                return integer * 2;
//            }
//            if (throwable != null) {
//                return 0;
//            }
//            return 0;
//        });
//        System.out.println(handle.get());
        /**
         * 返回结果
         * main...start...
         * 当前线程12
         * 运行结果5
         * 10
         * main...end...
         */


//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
        //笔记 不能接受上一步返回的结果
//        }, executor).thenRunAsync(() -> {
//            System.out.println("任务2执行了....");
//        }, executor);
        /**
         * 执行结果
         * main...start...
         * 当前线程12
         * 运行结果5
         * main...end...
         * 任务2执行了....
         */


//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
//            //笔记 能接受上一步的返回结果 但是不能修改
//        }, executor).thenAcceptAsync((integer) -> {
//            System.out.println("任务2执行了....上一步的返回值是：" + integer);
//        }, executor);
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * main...end...
         * 任务2执行了....上一步的返回值是：5
         */

//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果" + i);
//            return i;
//            //笔记 能接受上一步的返回结果 能自己返回一个结果
//        }, executor).thenApplyAsync((integer) -> {
//            System.out.println("任务2执行了....上一步的返回值是：" + integer);
//            return "你是猪";
//        }, executor);
//        System.out.println(stringCompletableFuture.get());
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务2执行了....上一步的返回值是：5
         * 你是猪
         * main...end...
         */


        //完成两个异步任务
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果" + i);
            return i;
        }, executor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("任务二...");
            return "hello";
        }, executor);


        //笔记  不能接受前面任务的返回值
//        future01.runAfterBothAsync(future02, () -> {
//            System.out.println("任务三...");
//        }, executor);
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务二...
         * main...end...
         * 任务三...
         */


        //笔记 可以接收前两个任务的返回结果
//        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
//            System.out.println("任务三...任务一结果" + f1 + "******  任务二返回结果：" + f2);
//        }, executor);
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务二...
         * main...end...
         * 任务三...任务一结果5******  任务二返回结果：hello
         */


        //笔记 得到前两个任务的返回值并且有自己的返回值
//        CompletableFuture<String> stringCompletableFuture = future01.thenCombineAsync(future02, (f1, f2) -> {
//            System.out.println("任务三...任务一结果" + f1 + "******  任务二返回结果：" + f2);
//            return f2 + f1;
//        }, executor);
//        System.out.println(stringCompletableFuture.get());
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务二...
         * 任务三...任务一结果5******  任务二返回结果：hello
         * hello5
         * main...end...
         */

        //笔记   两个任务只要有一个完成就执行任务三   不感知结果  而且不然会结果
//        future01.runAfterEitherAsync(future02, () -> {
//            System.out.println("任务三...");
//        }, executor);
        /**'
         * main...start...
         * 当前线程12
         * 运行结果5
         * main...end...
         * 任务三...
         * 任务二...
         *
         * 这里任务二的线程休眠了 3秒钟
         */

        //笔记  感知之前的结果但是自己不返回数据
//        future01.acceptEitherAsync(future02, (res) -> {
//            System.out.println("任务三..." + res);
//        }, executor);
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务二...
         * main...end...
         * 任务三...5
         */


//        CompletableFuture<String> stringCompletableFuture = future01.applyToEitherAsync(future02, (res) -> {
//            System.out.println("任务三..." + res);
//            return "hello" + res + "hhhh";
//        }, executor);
//        System.out.println(stringCompletableFuture.get());
        /**
         * main...start...
         * 当前线程12
         * 运行结果5
         * 任务二...
         * 任务三...5
         * hello5hhhh
         * main...end...
         */


        //笔记  组合多任务
        CompletableFuture<String> img = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片");
            return "img";
        }, executor);

        CompletableFuture<String> attr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性");
            return "attr";
        }, executor);

        CompletableFuture<String> brand = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品品牌");
            return "brand";
        }, executor);


        CompletableFuture<Void> allOf = CompletableFuture.allOf(img, attr, brand);
        allOf.get();// 等待所有的任务完成

        System.out.println("main...end...");
    }


    public void thread(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main方法.....start....");

        //1、 继承thread类来启动异步线程  异步线程会在后台慢慢启动
//        Thread01 thread01 = new Thread01();
//        thread01.start();   //执行结果
        /**
         * main方法.....start....
         * main方法.....end....
         * 当前线程正在被执行...12
         * 运行结果...5
         */

        //2、 实现runnable接口
//        Runnable01 runnable01 = new Runnable01();
//        new Thread(runnable01).start();
        /**
         * 执行结果
         * main方法.....start....
         * main方法.....end....
         * 当前线程正在被执行...12
         * 运行结果...5
         */

        //3、实现Callable接口 调取方法后回返回一个结果
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable01());
        //FutureTask实现了runnabale接口所以开启该线程：
//        new Thread(integerFutureTask).start();
//
//        //笔记 等待new Thread(integerFutureTask).start();异步执行完成之后返回一个结果  之历史阻塞等待的  必须要获取到执行的结果才会执行下一步内容
//        Integer integer = integerFutureTask.get();
//        System.out.println(integer);

        /**
         * 执行结果
         * main方法.....start....
         * 当前线程正在被执行...12
         * 运行结果...5
         * 5
         * main方法.....end....
         */


        //4、线程池 可控资源  创建一个线程池  创建异步任务直接给线程池提交一个任务就可以了
        executor.execute(new Runnable01());//给线程池提交一个任务
        /**
         * 执行结果
         * main方法.....start....
         * main方法.....end....
         * 当前线程正在被执行...12
         * 运行结果...5
         */


        //笔记 创建线程池的方式
        // 用juc提供的Executors工具类
        //笔记 原生创建线程池方式：  ThreadPoolExecutor extends AbstractExecutorService ,   class AbstractExecutorService implements ExecutorService
        /**
         *
         * 笔记 线程池的七大参数：
         *  public ThreadPoolExecutor(
         *  int corePoolSize,   核心线程数  一直存存在  就算池中空闲  也会存在 一直等待新任务 除非设置核心线程数超时属性 allowCoreThreadTimeOut
         *  int maximumPoolSize, 线程池中最大的线程数量  控制资源并发
         *  long keepAliveTime,   存活时间   当线程空闲超过这个时间就会被释放掉  此处释放的线程数量是指最大的线程数量maximumPoolSize - 核心的线程数量corePoolSize
         *  TimeUnit unit,  时间单位
         *  BlockingQueue<Runnable> workQueue,    任务队列  如果任务有很多  就会把目前多的任务放到队列中  只要有空闲的线程就会从队列中取出最先放进队列的任务
         *  ThreadFactory threadFactory,   创建线程的工厂   可以自定义 这里指默认
         *  rejectedExecutionHandler handler   当线程，队列都满了的时候 存不了更多的任务按照我们指定的拒绝策略拒绝任务
         *        拒绝策略：
         *       笔记       new ThreadPoolExecutor.AbortPolicy()// 默认 当线程过多撑满最大线程的时候，就会抛出异常java.util.concurrent.RejectedExecutionException阻止系统继续运行
         *       笔记       new ThreadPoolExecutor.CallerRunsPolicy()//  调用者机制，当线程撑爆时就会由哪个线程调度到线程池就回退到哪个线程执行  此处是main线程调度到线程池执行，所以这里就会由main执行
         *       笔记       new ThreadPoolExecutor.DiscardOldestPolicy()//  抛弃等待队列中等待时间最久的任务
         *       笔记       new ThreadPoolExecutor.DiscardPolicy()//  当线程池被撑爆时直接放弃多余的任务
         *  ) {
         *
         *
         * 笔记  线程池内的运行流程：
         *      线程池创建好了  就会制定好核心数量的核心线程， 准备接受任务
         *      当核心线程满了就会把线程放进阻塞队列中，空闲的core就会自动去任务队列获取任务执行
         *      阻塞队列满了就要开启新线程执行  但是最多只能开启到maximumPoolSize指定的数量
         *      当maximumPoolSize满了之后就用拒绝策略拒绝任务
         *      maximumPoolSize执行完了 很多空闲，就会在指定的施放时间后释放掉线程
         *
         */
        ExecutorService Executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                //笔记  new LinkedBlockingQueue<>()  默认的是integer的最大数  但是会导致内存不足造成 OOM错误 所以需要定制
                new LinkedBlockingQueue<>(10000),
                //笔记  默认的线程工厂
                Executors.defaultThreadFactory(),
                //笔记  拒绝策略  使用默认的 丢弃策略  丢掉新进来的任务
                new ThreadPoolExecutor.AbortPolicy()
        );
//
//        Executors.newCachedThreadPool();//没有核心数量 最大线程池是int 的最大数  60秒回收一次  灵活回收线程
//        Executors.newFixedThreadPool(10);   //固定线程池  只有固定大小的核心线程数 最大线程数也是核心线程数
//
//        Executors.newScheduledThreadPool(10);  //做定时任务的线程池  任务调度用
//        Executors.newSingleThreadExecutor();  //单线程的线程池  核心和最大都只有1个

        System.out.println("main方法.....end....");
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
//            super.run();
            System.out.println("当前线程正在被执行..." + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
        }
    }

    public static class Runnable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程正在被执行..." + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
        }
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程正在被执行..." + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
            return i;
        }
    }

}
