package springmvc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * @author qqai
 * @createTime 2020/10/2 22:28
 * @description：
 */

@Data
public class User implements Serializable {
    private String name;
    private int age;
    private String sex;
    //标记 没测试出来
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
