package qqai.java.base;

/**
 * @author qqai
 * @createTime 2020/11/11 23:31
 * @description：匿名对象
 */

public class TestNewInterface {
    public static void main(String[] args) {
        new Interface() {
            @Override
            public void run() {
                System.out.println("interface");
            }
        };

        new Abstract() {
            @Override
            public void run() {
                System.out.println("abstract");
            }
        };
    }
}

abstract class Abstract {
    public abstract void run();
}

interface Interface {
    void run();
}
