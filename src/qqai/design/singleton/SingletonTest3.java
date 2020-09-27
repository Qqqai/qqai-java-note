package qqai.design.singleton;

/**
 * 描述：单例模式-懒汉式第一种  线程不安全
 *
 * @author qqai
 * @createTime 2020-08-21 11:11
 */

public class SingletonTest3 {
}

/**
 * 笔记 这种方式确实能够做到懒加载的效果  但是只能在单线程下使用
 * 笔记  这种方式应用到多线程的时候，一个线程进入到if语句还没有执行完毕  另一个线程在进入这个if语句  就可能造成创建很多个这样的对象， 所以 这种方式是线程不安全的
 */
class Singleton3 {
    //依旧定义一个单例对象
    private static Singleton3 SINGLETON = null;

    //笔记  提供一个静态方法获取这个对象 我们在需要去使用这个对象的时候调用这个方法来创建这个单例对象
    public static Singleton3 getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new Singleton3();
        }
        return SINGLETON;
    }

    //笔记  首先依旧私有化构造方法
    private Singleton3() {
    }

}
