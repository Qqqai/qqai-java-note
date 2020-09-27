package qqai.principle.ocp;


/**
 * 描述：开闭原则
 *
 * @author qqai
 * @createTime 2020-08-20 20:41
 */

public class Ocp {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Circle1());
        graphicEditor.drawShape(new Rectangle());
    }
}

/**
 * 笔记 这样就实现了功能又不违反ocp原则
 */
//绘制图形的类
class GraphicEditor {
    public void drawShape(Shape shape) {
        //笔记  继承的时候直接实现了绘画的方法所以这里可以直接调用
        shape.draw();
    }
}

//笔记 遵循开闭原则把这个类设计成抽象的  添加一个抽象方法
abstract class Shape extends GraphicEditor {
    public abstract void draw();
}

//笔记 子类继承的时候实现这个方法
class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("圆形");
    }
}

class Circle1 extends Shape {
    @Override
    public void draw() {
        System.out.println("矩形");
    }
}
