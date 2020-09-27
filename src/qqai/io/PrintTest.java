package qqai.io;

/**
 * @author qqai
 * @createTime 2020/9/27 20:12
 * @description：输出测试
 */

public class PrintTest {
    public static void main(String[] args) {
        String s = "abc";
        System.out.write(s.charAt(1));// 笔记 这个方法不能输出String类型数据
    }
}
