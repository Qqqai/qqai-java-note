package qqai.volatiletest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

/**
 * @author qqai
 * @createTime 2020/11/7 16:39
 * @description：volatile 标记 关注Volatile指令重排序.md
 */

public class TestVolatile {
    public static volatile int counter = 1;

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        counter = 2;
        System.out.println(counter);
        int c = 4;
        int d = 5;
    }
}

/*笔记 字节码：
    {
  public static volatile int counter;
    descriptor: I
    flags: ACC_PUBLIC, ACC_STATIC, ACC_VOLATILE  笔记 标志位

  public qqai.volatiletest.TestVolatile();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 13: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: iconst_2
         1: putstatic     #2                  // Field counter:I  笔记 静态复制
         4: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: getstatic     #2                  // Field counter:I  笔记 静态取值
        10: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        13: return
      LineNumberTable:
        line 17: 0
        line 18: 4
        line 19: 13

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: iconst_1
         1: putstatic     #2                  // Field counter:I
         4: return
      LineNumberTable:
        line 14: 0
}
SourceFile: "TestVolatile.java"

*/