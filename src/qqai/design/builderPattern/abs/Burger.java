package qqai.design.builderPattern.abs;

import qqai.design.builderPattern.inter.Item;
import qqai.design.builderPattern.inter.Packing;
import qqai.design.builderPattern.inter.impl.Wrapper;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-14 17:47
 */

public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
