package qqai.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述：入口
 *
 * @author qqai
 * @createTime 2020-09-26 19:56
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("启动成功......");
        MessageService bean = context.getBean(MessageService.class);
        System.out.println(bean.getMessage());
    }
}
