package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.entity.User;

import java.util.Date;

/**
 * @author qqai
 * @createTime 2020/10/2 22:31
 * @description：测试spring提供的date格式化注解
 */

@Controller
public class TestDateAnnoController {

    @GetMapping("/user")
    @ResponseBody
    public User user(/*Model model*/) {
        User user = new User();
        user.setAge(31);
        user.setBirthday(new Date());
        user.setName("qqai");
        user.setSex("男");
//        model.addAttribute("user", user);
        return user;
    }
}
