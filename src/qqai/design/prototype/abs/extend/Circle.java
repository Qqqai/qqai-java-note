package qqai.design.prototype.abs.extend;

import qqai.design.prototype.abs.Shape;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-15 9:36
 */

public class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
