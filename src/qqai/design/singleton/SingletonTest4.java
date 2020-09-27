package qqai.design.singleton;

/**
 * 描述：单例模式  懒汉式  线程安全写法
 *
 * @author qqai
 * @createTime 2020-08-21 11:55
 */

public class SingletonTest4 {
}

/**
 * 笔记  线程安全的懒汉式加载方法
 */
class Singleton4 {
    private static Singleton4 singleton;

    private Singleton4() {
    }

    //笔记 在公共的获取方法上加上一个synchronized关键字给这个方法加锁，每个时间点只能有一个线程能执行这个方法  就解决了线程不安全的问题
    //笔记 但是这种方法在实际开发中效率太低
    public static synchronized Singleton4 getInstance() {
        if (singleton == null) {
            singleton = new Singleton4();
        }
        return singleton;
    }
}
