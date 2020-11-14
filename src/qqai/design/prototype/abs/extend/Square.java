package qqai.design.prototype.abs.extend;

import qqai.design.prototype.abs.Shape;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-15 9:35
 */

public class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
