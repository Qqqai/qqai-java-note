package qqai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author qqai
 * @createTime 2020/11/18 00:21
 * @description：eureka服务启动类
 */

@SpringBootApplication
//表示这个是eureka的注册中心
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class);
    }
}
