package qqai.design.bridge.inter.impl;

import qqai.design.bridge.inter.DrawAPI;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 13:22
 */

public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}