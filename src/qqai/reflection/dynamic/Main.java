package qqai.reflection.dynamic;

import com.sun.security.ntlm.Client;

import javax.security.auth.Subject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 描述：调用类
 *
 * @author qqai
 * @createTime 2020-08-21 22:34
 */

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HelloBean hello = new HelloBean();
        DynamicProxy proxy = new DynamicProxy(hello);  //把动态代理的对象传递给代理类
        HelloInterface helloInterface = (HelloInterface) proxy.getObject();  //这个方法获取到代理之后的对象
        helloInterface.sayHello();  //增前后的方法
        String s = helloInterface.getHello();
        System.out.println(s);
    }
}

