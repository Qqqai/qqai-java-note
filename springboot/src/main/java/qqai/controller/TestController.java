package qqai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qqai
 * @createTime 2020/10/26 21:20
 * @description：压力测试
 */

@RestController
public class TestController {
    private static int count = 0;

    // 标记 接口做过压力测试
    @RequestMapping(value = "/test", method = {RequestMethod.DELETE, RequestMethod.GET})
    public int test() {
        System.out.println(++count);
        return count;
    }
}
