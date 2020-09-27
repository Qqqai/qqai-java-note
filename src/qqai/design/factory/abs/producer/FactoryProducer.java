package qqai.design.factory.abs.producer;

import qqai.design.factory.abs.factory.AbstractFactory;
import qqai.design.factory.abs.factory.extend.ColorFactory;
import qqai.design.factory.abs.factory.extend.ShapeFactory;

/**
 * 描述：创建一个工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂。
 *
 * @author qqai
 * @createTime 2020-09-14 16:18
 */

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            System.out.println("FactoryProducer..SHAPE");
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            System.out.println("FactoryProducer..COLOR");
            return new ColorFactory();
        }
        return null;
    }
}
