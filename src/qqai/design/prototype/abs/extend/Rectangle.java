package qqai.design.prototype.abs.extend;

import qqai.design.prototype.abs.Shape;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-15 9:33
 */

public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
