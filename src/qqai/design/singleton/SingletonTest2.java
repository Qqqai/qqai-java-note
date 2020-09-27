package qqai.design.singleton;

/**
 * 描述：单例模式-饿汉式第二种
 *
 * @author qqai
 * @createTime 2020-08-21 11:03
 */

public class SingletonTest2 {

}

/**
 * 笔记 在静态代码块中创建单例对象
 * 笔记   这种方式初始化单例对象和直接初始化单例对象的意义其实是一样的   还是容易造成内存浪费
 */
class Singleton2 {
    //笔记 依旧私有化构造器
    private Singleton2() {
    }

    //笔记 在静态代码块中初始化这个单例对象  窘态代码块只会执行一次 所以这个对象一直是单例的
    static {
        singleton2 = new Singleton2();
    }

    //单例的对象
    private static final Singleton2 singleton2;

    //共有的获取方法
    public static Singleton2 getInstance() {
        return singleton2;
    }
}
