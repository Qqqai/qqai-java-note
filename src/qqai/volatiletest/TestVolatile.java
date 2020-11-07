package qqai.volatiletest;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author qqai
 * @createTime 2020/11/7 16:39
 * @description：volatile
 */

//TODO  没弄
public class TestVolatile {
    public static volatile int counter = 1;

    public static void main(String[] args) {
        counter = 2;
        System.out.println(counter);
    }
}
