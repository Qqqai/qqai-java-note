package qqai.design.singleton;

/**
 * 描述：单例模式  枚举实现初始化
 *
 * @author qqai
 * @createTime 2020-08-21 12:27
 */

public class SingletonTest7 {

    public static void main(String[] args) {
        Singleton7 singleton = Singleton7.SINGLETON;
        System.out.println(singleton == Singleton7.SINGLETON);  //笔记 这个对象只创建一次
        singleton.sayHello();
    }

}

/**
 * 笔记 枚举实现单例模式，
 */
enum Singleton7 {
    SINGLETON;

    public void sayHello() {
        System.out.println("ok...");
    }
}
