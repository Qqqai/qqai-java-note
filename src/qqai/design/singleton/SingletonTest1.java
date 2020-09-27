package qqai.design.singleton;

/**
 * 描述：单例模式-饿汉式
 *
 * @author qqai
 * @createTime 2020-08-21 10:40
 */

public class SingletonTest1 {


}

/**
 * 笔记 饿汉式
 * 笔记   这种方式的缺点  只要这个类被装载了 这个对象就会被实例化，做不到懒加载的效果 就会极大的浪费内存  但是这样的好处就是不会出现多线程的问题
 */
class Singleton1 {

    //笔记 在本类内部创建一个对象  这样在调用这个类的时候用的一直都是这个对象  就不会再出现别的对象
    private static final Singleton1 SINGLETON = new Singleton1();

    //笔记 对外提供一个共有的静态方法返回这个实例对象即可
    public static Singleton1 getInstance() {
        return SINGLETON;
    }

    //笔记  先将构造器私有化  外部就不能new创建这个对象了
    private Singleton1() {
    }

}