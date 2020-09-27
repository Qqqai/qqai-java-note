package qqai.design.bridge.abs;

import qqai.design.bridge.inter.DrawAPI;

/**
 * 描述：使用 DrawAPI 接口创建抽象类 Shape
 *
 * @author qqai
 * @createTime 2020-09-15 13:19
 */

public abstract class Shape {
    //需要桥接的对象
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}