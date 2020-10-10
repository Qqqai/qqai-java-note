package springmvc.controller;

import mybatis.entity.Student;
import mybatis.mapper.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/2 22:31
 * @description：测试spring提供的date格式化注解
 */

@Controller
public class TestDateAnnoController {

    @Autowired
    StudentDao studentDao;

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

    @GetMapping("/mybatis")
    @ResponseBody
    public List<Student> student() {
        return studentDao.queryAll();
    }
}
