package qqai.juc;

/**
 * 描述：引用类型值测试
 *
 * @author qqai
 * @createTime 2020-09-08 15:25
 */


public class TestValue {

    public void testChangeStringValue(String str) {
        str = "ccc";
        System.out.println(str);
    }

    public static void main(String[] args) {
        TestValue value = new TestValue();
        String str = "abc";
        value.testChangeStringValue(str);
        System.out.println(str);
    }

}
