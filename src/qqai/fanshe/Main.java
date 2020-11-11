package qqai.fanshe;

import java.lang.reflect.Field;

/**
 * @author qqai
 * @createTime 2020/11/6 01:36
 * @description：反射
 */

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> name = Class.forName("java.lang.String");
        Class<String> stringClass = String.class;
        System.out.println(stringClass.getSimpleName());  // 获取类名
//        for (Field declaredField : name.getDeclaredFields()) {
//            System.out.println(declaredField.getName()); // 获取各个字段名
//        }
    }
}
