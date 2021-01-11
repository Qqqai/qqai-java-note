package qqai.anno;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author qqai
 * @createTime 2020/12/27 14:23
 */

// 表示注解可以注册在哪些位置
@Target({ElementType.TYPE})
// java 文档注解
@Documented
//source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
//class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
//runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {

    String name();

    int age() default 18;

    int[] array() default {122, 3, 4};

}

/**
 * public enum ElementType {
 * /** 类，接口（包括注解类型）或枚举的声明
 * TYPE,
 * <p>
 * 属性的声明
 * FIELD,
 * <p>
 * 方法的声明
 * <p>
 * METHOD,
 * <p>
 * 方法形式参数声明
 * <p>
 * PARAMETER,
 * <p>
 * 构造方法的声明
 * CONSTRUCTOR,
 * <p>
 * 局部变量声明
 * LOCAL_VARIABLE,
 * <p>
 * 注解类型声明
 * ANNOTATION_TYPE,
 * <p>
 * 包的声明
 * PACKAGE
 * }
 */