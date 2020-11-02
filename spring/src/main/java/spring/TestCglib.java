package spring;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qqai
 * @createTime 2020/9/28 14:56
 * @description：测试spring自带的cglib动态代理
 */

public class TestCglib {
    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\idea\\sources\\jvm\\qqai\\spring\\src\\main\\java\\spring");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);
        //设置回调代理类对象
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        Dog proxyDog = (Dog) enhancer.create();
        //调用代理类的eat方法
        proxyDog.eat();
        proxyDog.run("kitty");
    }

}

class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！   cglib代理  具体实现看不懂
        Object object = proxy.invokeSuper(obj, args);
        System.out.println(object);//方法执行返回值
        return object;
    }
}

class Dog {

    final public void run(String name) {
        System.out.println("狗" + name + "----run");
    }

    public int eat() {
        System.out.println("狗----eat");
        return 1;
    }
}