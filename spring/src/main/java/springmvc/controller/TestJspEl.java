package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author qqai
 * @createTime 2020/10/10 16:56
 * @description：测试jspEl表达式失效
 */

@Controller
public class TestJspEl {
    @GetMapping("/testEl")
    public ModelAndView testEl() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "qqai");
        modelAndView.setViewName("user-view");
        return modelAndView;
    }
}
