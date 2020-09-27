package qqai.design.factory.abs.product;

import design.factory.abs.inter.Shape;

/**
 * 描述：接口实现
 *
 * @author qqai
 * @createTime 2020-09-14 16:06
 */

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle...");
    }
}
