package qqai.design.factory.abs.product;

import design.factory.abs.inter.Color;

/**
 * 描述：颜色实现2
 *
 * @author qqai
 * @createTime 2020-09-14 16:10
 */

public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("green...");
    }
}
