package qqai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import qqai.filter.FilterOne;
import qqai.filter.FilterTwo;

/**
 * @author qqai
 * @createTime 2020/12/14 15:02
 * @description：mvc配置
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FilterOne filterOne;

    @Autowired
    private FilterTwo filterTwo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors...");
        registry.addInterceptor(filterOne).addPathPatterns("/**").order(10);
        registry.addInterceptor(filterTwo).addPathPatterns("/**").order(1);
    }
}
