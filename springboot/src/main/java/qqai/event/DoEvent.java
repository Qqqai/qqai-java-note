package qqai.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import qqai.entity.User;
import qqai.listener.even.MyEven;

/**
 * 处理事件
 *
 * @author qqai
 * @createTime 2020/12/27 14:12
 */

@Component
public class DoEvent {

    @Autowired
    private ApplicationContext applicationContext;

    public void publish() {
        applicationContext.publishEvent(new MyEven(new User("qqai", 18)));
    }
}
