package qqai.mianshi.bianchengmiao;

/**
 * @author qqai
 * @createTime 2020/10/24 18:01
 * @description：编程喵
 */

public class Main2 {
    private static Main2 nioSocket = new Main2();
    private static int a = 0;

    static {
        System.out.println("111");
    }

    {
        System.out.println("222");
    }

    public static void main(String[] args) {
        Integer b = 0;
        String s1 = "abc";
        String s2 = new String(s1);
        String s3 = new String("abc");
        Integer integer = new Integer(28);
        Integer integer1 = new Integer(integer);
        System.out.println(a == b);
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(integer1 == integer);
        new Main2();
    }

}
