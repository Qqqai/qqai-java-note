package qqai.design.builderPattern.inter.impl;

import qqai.design.builderPattern.inter.Packing;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-14 17:44
 */

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "wrapper";
    }
}
