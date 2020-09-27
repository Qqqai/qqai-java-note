package qqai.principle.liskov;

/**
 * 描述：里氏替换原则
 *
 * @author qqai
 * @createTime 2020-08-20 19:25
 */

public class Liskov {

    public static void main(String[] args) {
        B b = new B();
        int i = b.func2(3, 2);
        System.out.println(i);
    }

}

class A extends Base {
    //返回两个数的差
    public int func1(int a, int b) {
        return a - b;
    }
}

class Base {

}

class B extends Base {

    //这样就不会重写A类的方法了
    private A a = new A();

    //笔记 本意是增加一个新的功能完成两个数的相加  但是重写了父类的方法 就表示本类就不再有两数之差这个方法
    //笔记 解决方法： 根据里氏替换原则再增加一个更为基础的基类Base 让A类和B类同时继承Base类
    //笔记  由于调用者没有继承A类 所以这个方法就不算是重写的方法
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

}


