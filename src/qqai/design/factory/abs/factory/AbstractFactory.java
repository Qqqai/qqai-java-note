package qqai.design.factory.abs.factory;

import qqai.design.factory.abs.inter.Color;
import qqai.design.factory.abs.inter.Shape;

/**
 * 描述：抽象工厂
 *
 * @author qqai
 * @createTime 2020-09-14 16:13
 */

public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
