package qqai.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * 描述：springioc分析
 *
 * @author qqai
 * @createTime 2020-09-26 18:59
 */

@Controller
public class A {

    @Autowired
    public B b;

    public void show() {
        System.out.println("hello");
    }
}
