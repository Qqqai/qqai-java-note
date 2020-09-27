package qqai.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 描述：反射
 *
 * @author qqai
 * @createTime 2020-08-21 14:16
 */

public class Test01 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> name = null;
        try {
            name = Class.forName("reflection.Student");//获取student对象的class代理对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class<? extends Student> aClass = Student.class;   //获取student对象的class代理对象

//        System.out.println(name);
//        System.out.println(aClass);
//        //笔记 在jvm中一个类只能由一个Class实例  一个Class实例对应的是加载到JVM中的.class文件
//        //笔记 通过这个Class对象  由这个类创建的所有对象对能找到一个类在JVM中被加载的完整结构
//        System.out.println(name == aClass);

        assert name != null;
        Student nameInterfaces = (Student) name.newInstance();  //默认调用无参构造器
//        System.out.println(nameInterfaces.toString());

        Constructor<?> constructor = name.getDeclaredConstructor(String.class, int.class, int.class);
        Student qqai = (Student) constructor.newInstance("qqai", 21, 1);
        System.out.println(qqai);

    }
}

class Student {
    private String name;
    private int age;
    private int id;

    public Student() {
    }

    public Student(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}