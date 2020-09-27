package qqai.design.factory.pizzafactory.pizza;

/**
 * 描述：具体的实现类
 *
 * @author qqai
 * @createTime 2020-08-21 13:49
 */

public class BlueberryPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给蓝莓披萨准备材料中");
    }
}
