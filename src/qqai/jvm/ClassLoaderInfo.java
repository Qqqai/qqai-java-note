package qqai.jvm;

/**
 * 描述：类加载器详细
 *
 * @author qqai
 * @createTime 2020-09-02 18:42
 */

public class ClassLoaderInfo extends A {

    public static void main(String[] args) {
        Object o = new Object();
        String s = "1211";
//        System.out.println(o.getClass().getClassLoader());
//        System.out.println(s.getClass().getClassLoader());

        ClassLoaderInfo info = new ClassLoaderInfo();

        info.getClass().getClassLoader().getParent();

//        System.out.println(info.getClass().getClassLoader());
//        System.out.println(info.getClass().getClassLoader().getParent());
//        System.out.println(info.getClass().getClassLoader().getParent().getParent());



        System.out.println();
    }

}

class A {
    public String a = "111";

    public A() {
    }

    private A(String a) {
        this.a = a;
    }
}