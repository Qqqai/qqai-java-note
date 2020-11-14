package qqai.design.bridge.abs.extend;

import qqai.design.bridge.inter.DrawAPI;
import qqai.design.bridge.abs.Shape;

/**
 * 描述：创建实现了 Shape 接口的实体类
 *
 * @author qqai
 * @createTime 2020-09-15 13:20
 */

public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        //调用桥接的方法
        drawAPI.drawCircle(radius, x, y);
    }
}