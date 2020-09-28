package spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import qqai.service.TestService;

/**
 * @author qqai
 * @createTime 2020/9/28 13:10
 * @description：测试ioc
 */

public class TestIoc {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestService bean = classPathXmlApplicationContext.getBean(TestService.class);
        bean.sayHello();
    }

}
