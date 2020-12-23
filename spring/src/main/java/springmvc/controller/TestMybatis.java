package springmvc.controller;

import mybatis.entity.Student;
import mybatis.mapper.StudentDao;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.mybatis.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试dao
 *
 * @author qqai
 * @createTime 2020/12/21 18:33
 */
@RestController
public class TestMybatis {

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/test/mybatis")
    public String test(String name) {
        studentDao.insert(new Student().setAge(18).setBirthday(new Date()).setName(name));
        return "success";
    }
}
