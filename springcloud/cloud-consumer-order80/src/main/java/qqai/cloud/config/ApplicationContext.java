package qqai.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author qqai
 * @createTime 2020/11/17 22:32
 * @description：配置
 */

@Configuration
public class ApplicationContext {

    @Bean
    // 开启restTemplate的负载均衡机制  不然只会找一个服务但是肯定不会是一个服务 所以就会出现崩溃现象  默认的机制是轮询
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
