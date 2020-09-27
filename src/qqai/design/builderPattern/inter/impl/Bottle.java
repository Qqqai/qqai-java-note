package qqai.design.builderPattern.inter.impl;

import qqai.design.builderPattern.inter.Packing;

/**
 * 描述：实现2
 *
 * @author qqai
 * @createTime 2020-09-14 17:45
 */

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "bottle";
    }
}
