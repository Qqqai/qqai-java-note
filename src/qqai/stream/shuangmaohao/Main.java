package qqai.stream.shuangmaohao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/4 18:59
 * @description：jdk8双冒号测试 笔记 双冒号表示可以调用类的某一个静态方法
 */

public class Main {
    public Main(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.forEach(item -> {
            Main.print(item);
        });
        list.forEach(Main::print);
        //标记 以上两种方法等价
        //标记 此处调用的是Main类的构造方法
        list.forEach(Main::new);
    }

    public static void print(String txt) {
        System.out.println(txt);
    }
}
