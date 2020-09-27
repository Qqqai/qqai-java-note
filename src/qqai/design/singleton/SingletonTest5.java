package qqai.design.singleton;

/**
 * 描述：单例模式  双重校验
 *
 * @author qqai
 * @createTime 2020-08-21 12:03
 */

public class SingletonTest5 {
}

//如果不用volatile关键字，有可能会出现异常。因为instance=new SingleTon();并不是一个原子操作。
// 线程A的操作会被编译成三条指令：(A1)分配对象的内存空间、(A2)初始化对象、(A3)设置instance指向内存空间。
//但是这个被返回的instance是有问题的——它还没有被初始化（A2还未被执行)。
//volatile关键字的作用：保证了变量的可见性（visibility）。被volatile关键字修饰的变量，如果值发生了变更，其他线程立马可见，避免出现脏读的现象。
//volatile  我的理解就是  在内存中可见的
class Singleton5 {
    private static volatile Singleton5 singleton;

    private Singleton5() {
    }

    /**
     * 笔记 双重校验的方式  在第一次判断这个对象为null之后会进入同步代码块，只有一个线程会进入这个同步代码块，当他再次判断这个对象的时候如果是null的话
     * 笔记  才会进入创建代码进行创建对象， 就算下一个线程进入了第一个if判断  当他获取到同步代码块的内容的时候这个对象已经创建了 就不会再创建对象，
     * 笔记  其他的线程  可能连第一个if语句都进入不了  所以这个方式是线程安全且单例的还满足了懒加载的模式
     *
     * @return
     */
    public static Singleton5 getInstance() {
        if (singleton == null) {
            synchronized (Singleton5.class) {
                if (singleton == null) {
                    singleton = new Singleton5();
                }
            }
        }
        return singleton;
    }
}