package qqai.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 描述：测试java.lang.relect.Constructor类下的newInstance方法创建对象
 *
 * @author qqai
 * @createTime 2020-09-15 16:09
 */

public class ConstructorInstance {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<A> constructor = A.class.getDeclaredConstructor(String.class);
        Class<? extends A> aClass = A.class;
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getName());
        System.out.println(aClass.getResource("/"));
        constructor.setAccessible(true);
        A student = constructor.newInstance("qqai");
        System.out.println(student.a);
    }
}
