package qqai.principle.segregation.improve;

/**
 * 描述：改进
 *
 * @author qqai
 * @createTime 2020-08-20 18:01
 */

public class Segregation2 {
    public static void main(String[] args) {
        new A().depend1(new B());
        new A().depend2(new B());

        new C().depend1(new D());
        new C().depend5(new D());
    }
}

/**
 * 笔记  当实现这个接口得时候，所有得实现类都需要实现接口中得方法，
 * 笔记   然而很多类不需要这么多方法，这就不满足接口隔离性原则
 * 笔记   所以我们把接口抽取成几个接口  当需要哪些方法得时候就实现实现那些接口
 */
interface interface1 {
    void run1();

}

interface interface2 {
    void run2();

    void run3();
}

interface interface3 {
    void run4();

    void run5();
}

//B类只需要实现需要实现得方法就实现接口1和2
class B implements interface1, interface2 {
    public void run1() {
        System.out.println("interface1 - run1");
    }

    public void run2() {
        System.out.println("interface2 - run2");
    }

    public void run3() {
        System.out.println("interface2 - run3");
    }
}

//D类只需要方法1，4，5  所以只需要实现实现接口1和3
class D implements interface1, interface3 {
    public void run1() {
        System.out.println("interface1 - run1");
    }

    public void run4() {
        System.out.println("interface3 - run4");
    }

    public void run5() {
        System.out.println("interface3 - run5");
    }
}

//A类通过接口依赖于B类得1，2，3方法  所以就让他直接依赖于1和2两个接口
class A {
    public void depend1(interface1 i) {
        i.run1();
    }

    public void depend2(interface2 i) {
        i.run2();
    }

    public void depend3(interface2 i) {
        i.run3();
    }
}

//C类通过接口依赖于D类得1，4，5方法  所以就让他直接依赖于1和3两个接口
class C {
    public void depend1(interface1 i) {
        i.run1();
    }

    public void depend4(interface3 i) {
        i.run4();
    }

    public void depend5(interface3 i) {
        i.run5();
    }
}

