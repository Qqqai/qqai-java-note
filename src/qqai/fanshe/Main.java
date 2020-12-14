package qqai.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author qqai
 * @createTime 2020/11/6 01:36
 * @description：反射
 */

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> name = Class.forName("java.lang.String");
        Class<String> stringClass = String.class;
        // 获取一个参数的构造器
        Constructor<String> constructor = stringClass.getConstructor(String.class);
        // 实例化对象
        String abc = constructor.newInstance("abc");
        // 看看这个对象保存的是什么字符串
        System.out.println(abc);
        System.out.println(stringClass.getSimpleName());  // 获取类名
//        for (Field declaredField : name.getDeclaredFields()) {
//            System.out.println(declaredField.getName()); // 获取各个字段名
//        }
    }
}
