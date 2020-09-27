package qqai.design.builderPattern.abs.extend;

import qqai.design.builderPattern.abs.ColdDrink;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-14 18:08
 */

public class Coke extends ColdDrink {
    @Override
    public float price() {
        return 8f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
