package qqai.design.factory.abs.product;


import qqai.design.factory.abs.inter.Shape;

/**
 * 描述：接口实现2
 *
 * @author qqai
 * @createTime 2020-09-14 16:07
 */

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square...");
    }
}
