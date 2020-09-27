package qqai.design.bridge.inter;

/**
 * 描述：桥接实现接口。
 *
 * @author qqai
 * @createTime 2020-09-15 11:36
 */

public interface DrawAPI {
    //需要桥接的类
    public void drawCircle(int radius, int x, int y);
}