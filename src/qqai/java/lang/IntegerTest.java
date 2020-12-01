package qqai.java.lang;

import qqai.java.base.Extend;

/**
 * 描述：测试Integer
 *
 * @author qqai
 * @createTime 2020-09-22 14:41
 */

public class IntegerTest {
    public static void main(java.lang.String[] args) {
//        Integer i1 = 23;
//        int i2 = 23;
//        Integer i3 = Integer.valueOf(23);
//        Integer i4 = new Integer(23);
//
//        System.out.println(i1 == i2);
//        System.out.println(i1 == i3);
//        System.out.println(i3 == i4);//false
//        System.out.println(i2 == i4);

        java.lang.String str1 = new StringBuilder("计算机").append("软件").toString();
        java.lang.String str2 = str1.intern();
        // java字面量已经存在于常量池中
        java.lang.String str3 = new StringBuilder("ja").append("va").toString();
        java.lang.String str4 = str3.intern();
        System.out.println(str1 == str2);
        System.out.println(str3 == str4);

    }


}

class B extends Extend {

    public int getAge() {
        return age;
    }

    private int x = 0;
}