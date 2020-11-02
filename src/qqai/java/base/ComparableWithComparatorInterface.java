package qqai.java.base;

import java.util.*;

/**
 * @author qqai
 * @createTime 2020/11/1 20:39
 * @description：测试Comparable和Comparator
 */

public class ComparableWithComparatorInterface {
    public static void main(String[] args) {
        ComparableTest a = new ComparableTest().setA(4);
        ComparableTest b = new ComparableTest().setA(3);
        // 笔记 调用实现的方法进行比较
        int i = a.compareTo(b);
        System.out.println(i);
    }
}

/**
 * 笔记 想要是用Comparable接口就需要实现这个接口，这个接口只有一个方法
 */
class ComparableTest implements Comparable<ComparableTest> {

    private int a;

    /**
     * 笔记 这个类实现这个方法之后调用他这个方法的也是这个类的对象本身，参数就是另一个对象
     */
    public ComparableTest setA(int a) {
        this.a = a;
        return this;
    }

    @Override
    public int compareTo(ComparableTest o) {
        if (this.a > o.a) {
            return 1;
        }
        return 0;
    }
}