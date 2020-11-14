package qqai.jvm;

/**
 * 描述：模拟栈溢出错误
 *
 * @author qqai
 * @createTime 2020-09-04 19:37
 */

public class TestStackOverFlowError {

    public static void m() {
        //这个方法递归调用本身方法  当栈内存空间被用完了就会报错：
        //Exception in thread "main" java.lang.StackOverflowError  栈溢出错误
        m();
    }

    public static void main(String[] args) {
//        m();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(maxMemory);
        System.out.println(totalMemory);
    }
}
