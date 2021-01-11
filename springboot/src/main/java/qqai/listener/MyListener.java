package qqai.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import qqai.listener.even.MyEven;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义监听器
 *
 * @author qqai
 * @createTime 2020/12/27 13:40
 */

@Component
public class MyListener implements ApplicationListener<MyEven> {

    @Override
    public void onApplicationEvent(MyEven event) {
        System.out.println(event.toString() + "-------");
    }
}
