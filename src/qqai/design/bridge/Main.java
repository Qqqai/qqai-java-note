package qqai.design.bridge;

import qqai.design.bridge.abs.Shape;
import qqai.design.bridge.abs.extend.Circle;
import qqai.design.bridge.inter.impl.GreenCircle;
import qqai.design.bridge.inter.impl.RedCircle;

/**
 * 描述：桥接模式
 *
 * @author qqai
 * @createTime 2020-09-15 11:32
 */

//使用 Shape 和 DrawAPI 类画出不同颜色的圆。
public class Main {
    public static void main(String[] args) {
        //构造器 传递参数 最后一个参数表示需要桥接的类型
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());
        //调用实现类的绘制方法 这个方法会调用桥接的类的绘制方法
        redCircle.draw();
        greenCircle.draw();
    }
}
