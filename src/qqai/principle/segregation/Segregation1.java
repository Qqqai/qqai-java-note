package qqai.principle.segregation;

/**
 * 描述：接口隔离得第一个案例
 *
 * @author qqai
 * @createTime 2020-08-20 17:34
 */

public class Segregation1 {

}

/**
 * 笔记  当实现这个接口得时候，所有得实现类都需要实现接口中得方法，  然而很多类不需要这么多方法，这就不满足接口隔离性原则
 */
interface interface1 {
    void run1();

    void run2();

    void run3();

    void run4();

    void run5();
}

//B类实现接口
class B implements interface1 {
    public void run1() {
        System.out.println("run1");
    }

    public void run2() {
        System.out.println("run2");
    }

    public void run3() {
        System.out.println("run3");
    }

    public void run4() {
        System.out.println("run4");
    }

    public void run5() {
        System.out.println("run5");
    }
}

//D类实现接口
class D implements interface1 {
    public void run1() {
        System.out.println("run1");
    }

    public void run2() {
        System.out.println("run2");
    }

    public void run3() {
        System.out.println("run3");
    }

    public void run4() {
        System.out.println("run4");
    }

    public void run5() {
        System.out.println("run5");
    }
}

//A类通过接口依赖于B类得1，2，3方法
class A {
    public void depend1(interface1 i) {
        i.run1();
    }

    public void depend2(interface1 i) {
        i.run2();
    }

    public void depend3(interface1 i) {
        i.run3();
    }
}

//C类通过接口依赖于D类得1，4，5方法
class C {
    public void depend1(interface1 i) {
        i.run1();
    }

    public void depend4(interface1 i) {
        i.run4();
    }

    public void depend5(interface1 i) {
        i.run5();
    }
}

