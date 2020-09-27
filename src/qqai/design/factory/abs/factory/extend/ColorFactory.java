package qqai.design.factory.abs.factory.extend;

import qqai.design.factory.abs.factory.AbstractFactory;
import qqai.design.factory.abs.inter.Color;
import qqai.design.factory.abs.inter.Shape;
import qqai.design.factory.abs.product.Blue;
import qqai.design.factory.abs.product.Green;
import qqai.design.factory.abs.product.Red;

/**
 * 描述：颜色工厂
 *
 * @author qqai
 * @createTime 2020-09-14 16:17
 */

public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
