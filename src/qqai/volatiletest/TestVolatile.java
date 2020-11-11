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

//TODO  没弄
public class TestVolatile {
    public static volatile int counter = 1;

    public static void main(String[] args) {
//        counter = 2;
//        System.out.println(counter);
        System.out.println(new TestVolatile().longestCommonPrefix(new String[]{"ab", "a"}));
    }

    public int romanToInt(String s) {
        int count = 0;
        int preNum = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = getValue(s.charAt(i));
            if (preNum < value) {
                count -= preNum;
            } else {
                count += preNum;
            }
            preNum = value;
        }
        count += preNum;
        return count;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder s = new StringBuilder();
        int len = strs.length;
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < len; j++) {
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    return s.toString();
                }
                if (j + 1 == len) {
                    s.append(strs[0].charAt(i));
                }
            }
        }
        return s.toString();
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