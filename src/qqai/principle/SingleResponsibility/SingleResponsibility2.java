package qqai.principle.SingleResponsibility;

/**
 * 描述：单一职责原则第二个案例
 *
 * @author qqai
 * @createTime 2020-08-20 17:17
 */

public class SingleResponsibility2 {

    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托");
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }

}

/**
 * 笔记  这样就遵守了单一职责原则
 * 笔记   但是这样做  改动很大  要把一个类分解，并且改动客户端
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在公路上运行");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在天空上运行");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println("有一个交通工具" + vehicle + "在水中上运行");
    }
}