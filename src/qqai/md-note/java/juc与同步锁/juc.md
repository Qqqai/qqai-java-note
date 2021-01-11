# 									JUC

#### 1、什么是进程/线程：

​		进程就是计算机后天运行的一个一个程序，每一个程序都是一个进程。

​		一个进程又会对应多个线程，比如说：idea启动的只有一个进程但是它会自动启动很多的线程，比如，语法检查，自动提示等等。

#### 2、什么是并发，什么是并行：

​		多个**线程**同一时间点争取同一个资源就是并发。

​		多个**线程**在同一时间段同时执行

#### 3、线程的调度

​		<font color=red size=4>线程的方法  .start();   并不是调用这个start方法就会开启这个线程，而是让这个线程进入就绪态，具体的执行需要等到cpu和操作系统底层的调度</font>

#### 4、interface和lambda表达式

```java
/**
 * 描述：lambda写法
 *
 * @author qqai
 * @createTime 2020-09-06 22:32
 */

//笔记 标明是一个函数式接口  这个接口有且只能有一个抽象的方法  default方法表示的是在接口中是实现的方法  是在1.8之后才支持的方式可以通过接口的实现类直接调用
@FunctionalInterface
interface Foo {
    int add(int x, int y);

    default int div(int x, int y) {
        return x / y;
    }
}

public class LambdaTest {

    public static void main(String[] args) {
        //            System.out.println("********");
        Foo foo = Integer::sum;
        System.out.println(foo.add(3, 4));
        System.out.println(foo.div(10, 2));
    }

}
	
```

​			

#### `多线程8锁`：

​		**1 标准访问，清问先打印邮件还是短信?**

​		**2 邮件方法暂停4秒钟，请问先打印邮件还是短信?**

​      <font color=red size=4>**synchronized**关键字锁住的不是某一个方法，锁住的是整个方法所在的类**对象**,就是说**同一时间内不可能有两个线程同时进入资源类**</font>  

​		**3 新增一个普通方法helLo()，清问先打印邮件还是helLo ?**

​				<font color=red>hello方法没有synchronized关键字，所以再执行这个方法的时候不需要同步，也不需要判断这个对象是否被锁住了，所以他会最先执行</font> 

​		**4 两部手机，请问先打印邮件还是短信?**

​		锁定的就是对象

​		**5 两个静态同步方法，同一部手机，请问先打印邮件还是短信?**

​		不是同一个对象所以是异步执行

​		**6 两个静态同步方法，2部手机，清问先打印邮件还是短信?**

​			<font color=red>如果这个方法是静态的，那么这个锁定就是锁定的是Phone.class这个模板而不是这个对象，所以如果是静态方法的锁，那么不管有几个对象，都是那个线程先获得到锁，同步执行那个线程</font>

​		**7 1个普通同步方法,1个静态同步方法，1部手机，请问先打印邮件还是短信?**

​		<font color=red>静态同步方法锁定的是模板，普通同步方法锁定的是对象----this，模板锁定对对象的应用是不产生同步关联的，所以这里是异步执行的</font>

​		**8 1个普通同步方法,1个静态同步方法，2部手机，请问先打印邮件还是短信?**

​		同上

```java
import java.util.concurrent.TimeUnit;
/**
 * 描述：线程调度测试
 *
 * @author qqai
 * @createTime 2020-09-07 20:34
 */
public class PhoneTest {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        //主线程休眠100毫秒
        Thread.sleep(100);

        new Thread(phone::sendSMS, "B").start();

        new Thread(phone::hello, "C").start();
    }
}
class Phone {
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("sendSMS");
    }
    public void hello() {
        System.out.println("hello");
    }
}
```



#### `List集合线程问题：`	   list和set基本相同

```java
/**
 * 描述：List多线程测试
 *
 * @author qqai
 * @createTime 2020-09-07 21:57
 */

/**
 * 笔记  main方法执行之后，使用ArrayList当线程达到一定的数量的时候就可能出现异常 		java.util.ConcurrentModificationException，所以ArrayList是线程不安全的			
 * 笔记  解决方法：  1、换成Vector类这个类是线程安全的List接口实现类（不推荐的方式）
 * 笔记  解决方法    2、使用Collections.synchronizedList(new ArrayList<>())把一个线程不安全的ArrayList转换成一个线程安全的集合
 * 笔记  解决办法    3、使用juc提供的CopyOnWriteArrayList类也能操作一个线程安全的ArrayList
 * 笔记  对于CopyOnWriteArrayList   ->   一个线程安全的变体ArrayList ，其中所有可变操作（ add ， set ，等等）通过对底层数组的最新副本实现。
 */
public class ListThreadTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> v = new Vector<>();
        List<String> syList = Collections.synchronizedList(new ArrayList<>());
        List<String> onWriteArrayList = new CopyOnWriteArrayList<>();
		Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        Map<String, String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        
        //笔记 这个方法上加的有同步锁synchronized
        v.add("aaa");
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(8));
                System.out.println(Thread.currentThread().getName() + "->list:" + list);
            }, String.valueOf(i)).start();
        }
    }
}
/**
 * 对于CopyOnWriteArrayList:
 *     读写是不一样的操作，内部是公共读，同步锁
 *     读取：
 *     public E get(int index) {
 *         return get(getArray(), index);
 *     }
 *     写入：
 *     public boolean add(E e) {
 *         //juc锁
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *              //读取  获取到当前的List内容，复制一份
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             //新的List
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             //把要添加的内容放到Object数组的最后面
 *             newElements[len] = e;
 *             //更新CopyOnWriteArrayList的List   简单点说就是更新List的版本
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *              //解锁
 *             lock.unlock();
 *         }
 *     }
 */
```

​	

#### `HashMap底层是一个Node类型的数组+Node类型的链表+红黑树HashMap也是线程不安全的！`



#### `Callable接口`:

```java
/**
 * 描述：Callable接口测试
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
```



#### `三个常用的工具类`：

​	`CountDownLatch`:

```java
/**
 * 描述：工具类测试
 *
 * @author qqai
 * @createTime 2020-09-08 13:37
 */
public class JucUtilsTest {
    public static void main(String[] args) throws InterruptedException {
           /**
         * CountDownLatch原理： CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，当计数器的值变为8时，因await方法阻塞的线程会被唤醒，继续执行。
         */
        //笔记 定义一个CountDownLatch其中包含需要控制的线程有6个
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " -> 同学" + finalI + "走了");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //笔记 这里没有完成6个线程的话就会一直等待
        countDownLatch.await();
        //main线程执行完成
        System.out.println(Thread.currentThread().getName() + "班长关门了");
    }
}

```

#### `线程池`:

```java
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
        ExecutorService executor = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()//笔记 默认 当线程过多撑满最大线程的时候，就会抛出异常java.util.concurrent.RejectedExecutionException阻止系统继续运行
                //new ThreadPoolExecutor.CallerRunsPolicy()//笔记  调用者机制，当线程撑爆时就会由哪个线程调度到线程池就回退到哪个线程执行  此处是main线程调度到线程池执行，所以这里就会由main执行
                //new ThreadPoolExecutor.DiscardOldestPolicy()//笔记  抛弃等待队列中等待时间最久的任务
                //new ThreadPoolExecutor.DiscardPolicy()//笔记  当线程池被撑爆时直接放弃多余的任务
        );
        try {
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //终止线程池
            executor.shutdown();
        }
    }
}

```

