package qqai.design.prototype;

import design.prototype.abs.Shape;
import design.prototype.table.ShapeCache;

/**
 * 描述：原型模式
 *
 * @author qqai
 * @createTime 2020-09-15 8:45
 */

public class Main {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
