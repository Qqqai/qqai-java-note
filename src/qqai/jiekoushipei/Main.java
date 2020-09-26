package qqai.jiekoushipei;

import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述：测试接口如果是defult方法的话那么实现类可以不实现这个方法
 *
 * @author qqai
 * @createTime 2020-09-26 13:25
 */

/**
 * WebMvcConfigurer是boot——mvc的一个接口 用于配置springmvc的 这个方法中定义的方法全部是default方法，所以实现的时候可以只实现开发需要的方法，
 */
public class Main implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }
}
