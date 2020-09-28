package qqai.newlife;

/**
 * 描述：对象创建的过程
 *
 * @author qqai
 * @createTime 2020-09-16 7:59
 */
class NewClassBeginToEnd extends Father {
    private String nickname = "2s";
    public static int aaa = 90;
    public static final int bbb = 100;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public NewClassBeginToEnd() {
    }

    //我们来试试 执行代码块的时候父类的对象是否创建完成了
    {
        System.out.println(this.name);
        System.out.println(this.age);
        System.out.println(nickname);
    }

    //笔记 static类型的代码块在获取的时候必须获取的是静态类型的参数
    static {
        System.out.println("static....");
//        System.out.println("static...." + aaa);
    }

    /*这里我们是有参的构造，这个问题是对变量赋值先后的测试 所以我任务无参构造有没有是没有影响的*/
    public NewClassBeginToEnd(String nickname) {
        /*我这先进行对super的测试  看看对于父类来说创建的时间
        笔记  结果表明，super关键字调用父类构造之后，父类的创建早于一切关于子类的创建  包括子类变量的赋值也是在父类创建的后面*/
//        super("qqai", 12);
        /*然后我又把super关键字注释掉，父类的变量直接在本类初始化试了试 没有super关键字了 父类初始化只能调用无参构造器，所以父类需要无参构造器 并且变量给一个默认值
         * 再次测试  笔记 结果一样，所以测试表明，不管有没有super关键字，创建一个类的对象的时候，一定会先创建一个父类对象，
         *        标记 我的理解是：
         *               笔记  调用字类的构造器的时候会查找他的父类对象，然后初始化父类对象，
         *                笔记 类创建过程是类构造器查询此时父类,如果还有父类就调用此时父类的构造方法在查询父类的父类.....一直这样知道没有父类可寻，
         *                 笔记  然后执行变量初始化，变量默认值赋值，静态代码块，构造代码块，本类构造方法的对于本类的创建
         *                */
        System.out.println("NewClassBeginToEnd.....");
        this.nickname = nickname;
        System.out.println(nickname);
    }

//    public static void main(String[] args) {
////        System.out.println(NewClassBeginToEnd.aaa);
////        System.out.println(NewClassBeginToEnd.bbb);
////        new NewClassBeginToEnd("qqai-nickname");
//        System.out.println(Constant.VALUE);
//    }
}

class Constant {
    public static final double HELLO_WORLD = 1000D;
//    public static final String HELLO_WORLD = "hello world!";

    static {
        System.out.println("static111");
    }
}

public class Main {
    public static void main(String[] args) {
//        System.out.println(Constant.HELLO_WORLD);
        new NewClassBeginToEnd();
    }
}

class Father {
    public String name = "我们之间";
    public int age = 18;

    {
        System.out.println(this.name + "....f");
        System.out.println(this.age + "....f");
    }

    static {
        System.out.println("father static....");
    }

    public Father() {
        System.out.println("Father....noArgs");
    }

    public Father(String name, int age) {
        System.out.println("Father....");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}