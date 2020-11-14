package qqai.design.factory.pizzafactory.pizza;

/**
 * 描述：工厂模式  简单工厂  披萨工厂实例
 *
 * @author qqai
 * @createTime 2020-08-21 13:45
 */
public abstract class Pizza {
    String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + "bake");
    }

    public void cut() {
        System.out.println(name + "cut");
    }

    public void box() {
        System.out.println(name + "box");
    }

    public void setName(String name) {
        this.name = name;
    }
}
