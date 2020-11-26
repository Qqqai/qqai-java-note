package qqai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import qqai.rule.RibbonRule;

/**
 * @author qqai
 * @createTime 2020/11/17 22:10
 * @description：启动类
 */

@SpringBootApplication
@EnableEurekaClient
// 添加注解 修改默认的轮询规则为随机规则
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = RibbonRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class);
    }
}
