package qqai.service.impl;

import org.springframework.stereotype.Service;
import qqai.service.TestService;

/**
 * @author qqai
 * @createTime 2020/9/28 13:18
 * @description：
 */

@Service
public class TestServiceImpl implements TestService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
