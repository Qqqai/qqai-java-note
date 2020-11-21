package qqai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qqai
 * @createTime 2020/11/15 00:42
 * @description：启动类
 */

@SpringBootApplication
// 表明这个是eureka的客户端
@EnableEurekaClient
// 服务发现
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class);
    }
}
