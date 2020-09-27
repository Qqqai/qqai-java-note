package qqai.reflection.dynamic;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述：动态代理
 *
 * @author qqai
 * @createTime 2020-08-21 22:21
 */

public class DynamicProxy implements InvocationHandler {

    private Object object;  //笔记  被代理的对象

    /**
     * 笔记  构造器 实例化被代理的对象
     *
     * @param object
     */
    public DynamicProxy(Object object) {
        super();
        this.object = object;
    }

    /**
     * 笔记 增强方法
     *
     * @param proxy  代理对象
     * @param method 代理类通过该接口继承方法，调用哪个方法久会哪个方法久会进入这个代理  这个method就代表那个方法  值是方法名 反射方式获取到方法执行结果
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("增强前");
        Object re = method.invoke(object, args);//代理对象的方法和参数
        System.out.println("代理后");
//        System.out.println(re);  //如果这个代理的方法由返回值 可以在这里获得到
        return re;
    }

    /**
     * 笔记 提供给外界的获取代理后对象的对象
     *
     * @return
     */
    public Object getObject() {
        //object.getClass().getClassLoader()(代理对象的类加载器), object.getClass().getInterfaces()(指定的接口), this(本类)
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

}
