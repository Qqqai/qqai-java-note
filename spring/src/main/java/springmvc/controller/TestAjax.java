package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springmvc.entity.User;

import java.util.HashMap;
import java.util.Map;


/**
 * ajax测试
 *
 * @author qqai
 * @createTime 2020/12/16 23:16
 */
@Controller
public class TestAjax {

    @GetMapping("/ajax/to/page")
    public String toPage() {
        return "ajax";
    }

    @PostMapping("/ajax/test")
    @ResponseBody
    public Map<String, String> test(@RequestBody User user) {
        System.out.println(user);
        Map<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("age", user.getAge() + "");
        return map;
    }
//    @Data
//    static class User {
//        private String name;
//        private int age;
//        private String sex;
//        private String birthday;
//    }
}
