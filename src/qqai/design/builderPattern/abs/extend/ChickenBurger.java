package qqai.design.builderPattern.abs.extend;

import qqai.design.builderPattern.abs.Burger;

/**
 * 描述：继承
 *
 * @author qqai
 * @createTime 2020-09-14 17:51
 */

public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 25f;
    }
}
