package qqai.principle.SingleResponsibility;

/**
 * 描述：单一职责模式第一个案例
 *
 * @author qqai
 * @createTime 2020-08-20 17:06
 */

public class SingleResponsibility1 {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
        //有一个交通工具摩托车在公路上运行
        //有一个交通工具汽车在公路上运行
        //有一个交通工具飞机在公路上运行

        /**
         *笔记  很明显  飞机是不适合再公路上跑得  所以这里就违反了单一职责的原则
         *
         * 笔记   解决方法也很简单 把工具类分解成不同得几个类  比如水上得  空中  陆地得这种
         *
         */

    }

}

//交通工具类
class Vehicle {
    public void run(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在公路上运行");
    }
}
