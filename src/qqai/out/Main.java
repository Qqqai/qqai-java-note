package qqai.out;

/**
 * @author qqai
 * @createTime 2020/11/6 15:08
 * @description：out输出得原理
 */

public class Main {
    public static void main(String[] args) {
        // 用了BufferedWrite类得方法写出去得字符流  debug进去
        System.out.println(new Entity().setAge(18).setName("qqai"));
    }
}
