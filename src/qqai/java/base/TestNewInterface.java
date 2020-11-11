package qqai.java.base;

/**
 * @author qqai
 * @createTime 2020/11/11 23:31
 * @description：匿名对象
 */

public class TestNewInterface {
    public static void main(String[] args) {
        Interface i = new Interface() {
            @Override
            public void run() {
                System.out.println("interface");
            }
        };

        Abstract a = new Abstract() {
            @Override
            public void run() {
                System.out.println("abstract");
            }
        };

        Interface1 i1 = new Interface1() {
            @Override
            public void run() {
                System.out.println("interface");
            }

            @Override
            public void run(String s) {
                System.out.println("interface" + "-- > s -->" + s);
            }
        };
    }
}

abstract class Abstract {
    public abstract void run();
}

// 标记 这里不好这样写的话就是函数时接口  看不出所以然  应该多写几个方法
interface Interface {
    void run();
}

interface Interface1 {
    void run();

    void run(String s);
}

