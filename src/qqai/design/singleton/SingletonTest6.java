package qqai.design.singleton;

/**
 * 描述：单例模式 静态内部类实现初始化
 *
 * @author qqai
 * @createTime 2020-08-21 12:19
 */

public class SingletonTest6 {
}

/**
 * 静态内部类 既实现了懒加载也实现了线程安全
 */
class Singleton6 {
    private Singleton6() {
    }

    //笔记 定义一个静态内部类  在初始化Singleton6的时候，这个静态内部类是不会被立即初始化的
    //笔记  只有在调用getInstance这个方法的时候因为返回了这个类的属性 这个类才会被装载
    //笔记  而且这个类是Singleton6的静态属性只会被初始化一次， 所以jvm会帮我们处理线程安全的问题， 在这个类初始化的时候别的线城是不能进入的
    private static class SingletonInstance {
        private static final Singleton6 SINGLETON = new Singleton6();
    }

    //提供一个返回方法 直接返回这个静态内部类的对象
    public static Singleton6 getInstance() {
        return SingletonInstance.SINGLETON;
    }
}
