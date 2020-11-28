package qqai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qqai
 * @createTime 2020/11/28 18:26
 * @description：启动类 笔记 动态修改配置添加了
 * #暴漏监控端点
 * management:
 * endpoints:
 * web:
 * exposure:
 * include: "*"
 * <p>
 * 在controller也添加了@RefreshScope
 * 但是服务内部依旧是加载的以前的配置需要手动发送一个刷新请求才能完成刷新操作
 * 笔记 curl -X  POST "http://localhost:3355/actuator/refresh"
 */

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
