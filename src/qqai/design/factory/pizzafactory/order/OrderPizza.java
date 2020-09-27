package qqai.design.factory.pizzafactory.order;

import qqai.design.factory.pizzafactory.pizza.BlueberryPizza;
import qqai.design.factory.pizzafactory.pizza.GreekPizza;
import qqai.design.factory.pizzafactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：订购披萨的类
 *
 * @author qqai
 * @createTime 2020-08-21 13:53
 */
public class OrderPizza {
    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String type;  //订购的类型
        do {
            type = getType();
            if ("greek".equals(type)) {
                pizza = new GreekPizza();
                pizza.setName("greek");
            } else if ("blueberry".equals(type)) {
                pizza = new BlueberryPizza();
                pizza.setName("blueberry");
            } else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }
    //获取客户订购的披萨的种类
    private String getType() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("inout you order type:");
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
