package qqai.design.prototype.abs;

/**
 * 描述：抽象类
 *
 * @author qqai
 * @createTime 2020-09-15 9:31
 */

public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    public abstract void draw();

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
