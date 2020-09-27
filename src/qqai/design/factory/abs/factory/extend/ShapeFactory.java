package qqai.design.factory.abs.factory.extend;

import qqai.design.factory.abs.factory.AbstractFactory;
import qqai.design.factory.abs.inter.Color;
import qqai.design.factory.abs.inter.Shape;
import qqai.design.factory.abs.product.Circle;
import qqai.design.factory.abs.product.Rectangle;
import qqai.design.factory.abs.product.Square;

/**
 * 描述：圆形工厂接口
 *
 * @author qqai
 * @createTime 2020-09-14 16:14
 */

public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
