package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import spring.entity.Student;
import springmvc.service.TestService;

/**
 * @author qqai
 * @createTime 2020/9/28 13:10
 * @description：测试ioc
 */

public class TestIoc {

//    @Autowired
//    private Environment environment;

    public static void main(String[] args) {
        // 根据配置文件获取到上下文内容
        AbstractApplicationContext abstractApplicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        /*
        @Override
	    public <T> T getBean(Class<T> requiredType) throws BeansException {
		    assertBeanFactoryActive(); 判断ioc工厂状态
		    return getBeanFactory().getBean(requiredType);  源码！
	    }
        */
        // 根据需要的类的字节码从上下文对象中获取到该类经由反射创建出来的对象
        TestService bean = abstractApplicationContext.getBean(TestService.class);
        Student student = abstractApplicationContext.getBean(Student.class);
        student.setBeanName("qqai");
//        student.setEnvironment();
        // 方法
//        bean.sayHello();
    }

}
