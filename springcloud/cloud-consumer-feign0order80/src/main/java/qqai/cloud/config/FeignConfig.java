package qqai.cloud.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qqai
 * @createTime 2020/11/23 01:16
 * @description：openfeign配置
 */

@Configuration
public class FeignConfig {
//    @Bean
//    FeignClientProperties.FeignClientConfiguration feignClientConfiguration() {
//        FeignClientProperties.FeignClientConfiguration config = new FeignClientProperties.FeignClientConfiguration();
//        config.setConnectTimeout(5000);
//        config.setLoggerLevel(Logger.Level.FULL);
//        config.setReadTimeout(5000);
//        return config;
//    }

//    @Bean
//    Logger.Level feignLoggerLevel() {
//        // 设置日志级别 最高级别
//        return Logger.Level.FULL;
//    }
}
