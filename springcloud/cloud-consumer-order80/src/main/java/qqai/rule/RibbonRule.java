package qqai.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qqai
 * @createTime 2020/11/22 21:47
 * @description：rinbbon定制化负载均衡规则
 */

@Configuration
public class RibbonRule {
    @Bean
    public IRule iRule() {
        // 定制为随机规则
        return new RandomRule();
    }
}
