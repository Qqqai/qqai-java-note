package qqai.design.factory.abs.product;

import design.factory.abs.inter.Shape;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-14 16:16
 */

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle...");
    }
}
