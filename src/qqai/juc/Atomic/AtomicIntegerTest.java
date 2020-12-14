package qqai.juc.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qqai
 * @createTime 2020/12/14 16:02
 * @description：测试
 */

public class AtomicIntegerTest {
    private final AtomicInteger integer;

    public AtomicIntegerTest() {
        integer = new AtomicInteger(1);
    }

    public int add(int e) {
        integer.addAndGet(e);
        return integer.intValue();
    }

    public static void main(String[] args) {
        System.out.println(new AtomicIntegerTest().add(3));
    }

}
