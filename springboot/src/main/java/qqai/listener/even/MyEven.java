package qqai.listener.even;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author qqai
 * @createTime 2020/12/27 14:08
 */
public class MyEven extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEven(Object source) {
        super(source);
    }
}
