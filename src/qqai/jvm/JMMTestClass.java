package qqai.jvm;

/**
 * 描述：JMM内存模型
 *
 * @author qqai
 * @createTime 2020-09-06 20:03
 */

public class JMMTestClass {

    //笔记 加上这个关键字就表示在内存中这个参数对其他线程是共享的 只要某一个线程中这个值被修改了 那么其他共用线程也会立即修改
    volatile int num = 0;

    public void changeNumTo1025() {
        this.num = 1025;
    }

    public static void main(String[] args) {
        JMMTestClass jmmTestClass = new JMMTestClass();
        new Thread(() -> {
            //新建线程 测试共享变量再修改之后会怎么进行数据读取
            try {
                Thread.sleep(3000);
                jmmTestClass.changeNumTo1025();
                System.out.println(Thread.currentThread().getName() + "->num->" + jmmTestClass.num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        //main线程执行
        while (jmmTestClass.num == 0) {
            System.out.println("main->num->0");
        }
        //共享变量的值被修改了
        System.out.println(Thread.currentThread().getName() + "->num" + jmmTestClass.num);
    }

}
