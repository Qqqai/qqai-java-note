package qqai.synchronizedtest;

/**
 * 描述：synchronized关键字
 *
 * @author qqai
 * @createTime 2020-09-26 15:41
 */

public class Main {

    public static void main(String[] args) {

    }

    /**
     * 笔记 ！这个看文件！！！  synchronized关键字.md
     */
    public void func() {
        synchronized (this) {
            System.out.println("java!!!");
        }
        System.out.println("hello world!");
    }
}

