package qqai.juc;

/**
 * 描述：lambda写法
 *
 * @author qqai
 * @createTime 2020-09-06 22:32
 */

//笔记 标明是一个函数式接口  这个接口有且只能有一个抽象的方法  default方法表示的是在接口中是实现的方法  是在1.8之后才支持的方式可以通过接口的实现类直接调用
@FunctionalInterface
interface Foo {
    int add(int x, int y);

    default int div(int x, int y) {
        return x / y;
    }
}

public class LambdaTest {

    public static void main(String[] args) {
        //            System.out.println("********");
        Foo foo = Integer::sum;

        Foo foo1 = (x, y) -> x + y;

        System.out.println(foo.add(3, 4));
        System.out.println(foo.div(10, 2));
    }

}
