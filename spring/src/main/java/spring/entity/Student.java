package spring.entity;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author qqai
 * @createTime 2020/11/3 21:10
 * @description：血汗俄国类
 */

/**
 * Aware接口的作用就是对bean对象设置属性的
 */
public class Student implements BeanNameAware, EnvironmentAware {
    public String getBeanName() {
        return beanName;
    }

    private String beanName = null;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println(this.beanName);
    }

    @Override
    public void setEnvironment(Environment environment) {
        String s = environment.getProperty("project.name");
        System.out.println(s);
    }
}
