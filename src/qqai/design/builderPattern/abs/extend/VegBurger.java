package qqai.design.builderPattern.abs.extend;

import qqai.design.builderPattern.abs.Burger;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-14 18:07
 */

public class VegBurger extends Burger {
    @Override
    public float price() {
        return 30f;
    }

    @Override
    public String name() {
        return "VegBurger";
    }
}
