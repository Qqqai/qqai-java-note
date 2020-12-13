package qqai.java.base;

/**
 * @author qqai
 * @createTime 2020/11/30 13:45
 * @description：集成测试
 */

public abstract class Extend {
    protected int age;

    final void sendMsg(String msg) {
        System.out.println(msg);
    }

    public abstract int getAge();

    public static void main(String[] args) {

    }
}
