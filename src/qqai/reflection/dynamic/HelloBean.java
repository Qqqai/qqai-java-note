package qqai.reflection.dynamic;

/**
 * 描述：代理类
 *
 * @author qqai
 * @createTime 2020-08-21 22:26
 */

public class HelloBean implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("Hello zhanghao!");
    }

    @Override
    public String getHello() {
        return "hello";
    }
}