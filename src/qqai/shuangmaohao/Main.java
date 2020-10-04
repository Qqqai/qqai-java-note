package qqai.shuangmaohao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/4 18:59
 * @description：jdk8双冒号测试
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
        list.forEach(Main::new);
    }

    public static void print(String txt) {
        System.out.println(txt);
    }
}
