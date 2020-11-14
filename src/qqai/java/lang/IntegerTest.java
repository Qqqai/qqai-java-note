package qqai.java.lang;

/**
 * 描述：测试Integer
 *
 * @author qqai
 * @createTime 2020-09-22 14:41
 */

public class IntegerTest {
    public static void main(java.lang.String[] args) {
        Integer i1 = 23;
        int i2 = 23;
        Integer i3 = Integer.valueOf(23);
        Integer i4 = new Integer(23);

        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i3 == i4);//false
        System.out.println(i2 == i4);

    }


}

class B {
    private int x = 0;
}