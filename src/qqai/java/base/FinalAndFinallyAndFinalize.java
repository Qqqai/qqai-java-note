package qqai.java.base;

/**
 * @author qqai
 * @createTime 2020/11/1 20:18
 * @description：关键字取别
 */

public class FinalAndFinallyAndFinalize {


    public static void main(String[] args) {
        // 笔记 finally关键字
        try {
            throw new RuntimeException("测试finally关键字...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
        }

        //笔记 finalize是Object类的一个方法，当类的实例被垃圾回收器回收时回收时就会执行这个方法 不过这个方法被弃用了
        while (true) {
            System.out.println(new Final().funB());
            break;
        }
        System.out.println("end...");
    }

}

/*final*/ class Final {
    /**
     * 笔记 final可以作用在类、成员变量、方法上，分别表示类不可继承，方法不能覆盖，属性不可变
     */
    final String a = "a";

    String s = "test";

    public void test() {
        //笔记 报错Cannot assign a value to final variable 'a'
//        a = s;
    }

    final public String funA() {
        return "a";
    }

    public String funB() {
        return "B";
    }

    /*笔记  这个方法没有执行 所以这种写法可能错误*/
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize...");
        super.finalize();
    }
}

/*笔记 如果这个类有final关键字修饰编译报错 Cannot inherit from final 'qqai.java.base.Final' */
class TestExtendFinalClass extends Final {
    //笔记 A方法不能被继承  报错 'funA()' cannot override 'funA()' in 'qqai.java.base.Final';
    //笔记 overridden method is final
//    public String funA() {
//        return super.funA();
//    }

    @Override
    public String funB() {
        return super.funB();
    }

    //笔记 A方法可以被重写
    public String funA(String s) {
        return "a" + s;
    }
}