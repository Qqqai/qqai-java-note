package qqai.design.factory.pizzafactory.pizza;

/**
 * 描述：具体实现
 *
 * @author qqai
 * @createTime 2020-08-21 13:50
 */

public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给希腊披萨准备原材料各种");
    }
}
