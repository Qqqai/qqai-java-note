package qqai.thredpool;

import java.util.concurrent.*;

/**
 * 描述：线程池测试
 *
 * @author qqai
 * @createTime 2020-09-08 15:34
 */
public class TestThreadPool {
    /*笔记
        线程池的优势:
            线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。
            它的主要特点为:线程复用;控制最大并发数;管理线程。
            第一:降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的销耗。
            第二:提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行.
            第三∶提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会销耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
     */
    public static void main(String[] args) {
        /**
         * 根据阿里开发手册表明线程池的创建不允许使用Executors工具类！！！
         */
//        ExecutorService service = Executors.newFixedThreadPool(5);  //核心线程和最大线程都是5
//        Executors.newSingleThreadExecutor();//单线程
//        Executors.newCachedThreadPool();//一池多个线程 最大线程数是int的最大值
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
         *  )
         * 笔记  线程池内的运行流程：
         *      线程池创建好了  就会制定好核心数量的核心线程， 准备接受任务
         *      当核心线程满了就会把线程放进阻塞队列中，空闲的core就会自动去任务队列获取任务执行
         *      阻塞队列满了就要开启新线程执行  但是最多只能开启到maximumPoolSize指定的数量
         *      当maximumPoolSize满了之后就用拒绝策略拒绝任务
         *      maximumPoolSize执行完了 很多空闲，就会在指定的施放时间后释放掉线程
         *
         */
//        ExecutorService executor = new ThreadPoolExecutor(2,
//                5,
//                2L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(2),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()//笔记 默认 当线程过多撑满最大线程的时候，就会抛出异常java.util.concurrent.RejectedExecutionException阻止系统继续运行
//                //new ThreadPoolExecutor.CallerRunsPolicy()//笔记  调用者机制，当线程撑爆时就会由哪个线程调度到线程池就回退到哪个线程执行  此处是main线程调度到线程池执行，所以这里就会由main执行
//                //new ThreadPoolExecutor.DiscardOldestPolicy()//笔记  抛弃等待队列中等待时间最久的任务
//                //new ThreadPoolExecutor.DiscardPolicy()//笔记  当线程池被撑爆时直接放弃多余的任务
//        );
//        try {
//            for (int i = 0; i < 10; i++) {
//                executor.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //终止线程池
//            executor.shutdown();
//        }

        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executorService2.execute(new MyRunnableTask(i));
        }
    }
}

class MyRunnableTask implements Runnable {

    private int a;

    public MyRunnableTask(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "--->" + a);
    }
}
