package qqai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author qqai
 * @createTime 2020/11/28 16:49
 * @description：启动类
 */

@SpringBootApplication
@EnableEurekaClient
public class AppConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigCenterMain3344.class, args);
    }
}
