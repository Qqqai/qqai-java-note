package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author qqai
 * @createTime 2020/11/7 00:59
 * @description：
 */

@Controller
public class ListController {

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        request.setAttribute("list", list);
        return "list";
    }
}
