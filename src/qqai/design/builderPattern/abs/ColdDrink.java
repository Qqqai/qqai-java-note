package qqai.design.builderPattern.abs;

import qqai.design.builderPattern.inter.Item;
import qqai.design.builderPattern.inter.Packing;
import qqai.design.builderPattern.inter.impl.Bottle;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-14 17:48
 */

public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
