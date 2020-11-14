package qqai.java.lang;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-02 19:14
 */

public class String {
    /**
     * 笔记 双亲委派原则，这个类找的时候会先在最底层的类加载器找，然后加载的时候会先从bootstrap类加载器加载，
     * 笔记 找到的就是jdk rt.jar包里的string类 但是这个类并没有main方法所以就会异常
     *
     * @param args
     */

    public static void main(String[] args) {
        new java.lang.String("ada");
        new StringBuffer().append(1);
        new StringBuilder();
        System.out.println("hello world");
    }

}
