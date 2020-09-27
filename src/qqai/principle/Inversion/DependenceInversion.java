package qqai.principle.Inversion;

/**
 * 描述：依赖倒转
 *
 * @author qqai
 * @createTime 2020-08-20 18:48
 */

public class DependenceInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());

        person.receive(new WX());
    }

}

//电子邮件
class Email {
    public void getInfo() {
        System.out.println("电子邮件信息：HelloWorld");
    }
}

//person接收消息
class Person {
    /**
     * 笔记 这里得参数直接传递了一个Email类  所以这个类是依赖于Email这个类得
     * 笔记  如果我们获取的对象是 短信 ，微信，等等信息得话  就需要新增加类和方法来处理
     * 笔记  解决方法  我们在传递这个对象得时候  引入一个IRecevier得接口，表示接受得信息，  这样 Perser类只需要于IRecevier接口发生依赖即可
     * 笔记   因为微信  短信  等等 都属于接受者得范围所以 他们只需要实现IRecevier这个接口既可以了   这样就符合了我们所说得依赖倒转原则
     *
     * @param email
     */
    public void receive(Email email) {
        email.getInfo();
    }

    /**
     * 笔记 这就是面向接口编程得写法
     */
    public void receive(IReceiver receiver) {
        receiver.getInfo();
    }
}

interface IReceiver {
    void getInfo();
}

class WX implements IReceiver {
    public void getInfo() {
        System.out.println("这是微信接受得消息：....");
    }
}

class DX implements IReceiver {
    public void getInfo() {
        System.out.println("这是短信收到得消息：....");
    }
}