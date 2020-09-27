package qqai.principle.SingleResponsibility;

/**
 * 描述：单一职责第三个类
 *
 * @author qqai
 * @createTime 2020-08-20 17:24
 */

public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("轮船");
    }
}

/**
 * 笔记 这样就对原来方案一得方法没有做太大的修改  虽然没有在类上遵守单一职责原则，但是在方法上仍然是遵守单一职责原则得
 */
class Vehicle2 {
    public void run(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在公路上运行");
    }

    public void runAir(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在天空上运行");
    }

    public void runWater(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在水上运行");
    }
}