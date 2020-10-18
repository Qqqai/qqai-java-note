package qqai.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/15 23:33
 * @description：测试2
 */

public class test02 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(12);
        list.add(123);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
