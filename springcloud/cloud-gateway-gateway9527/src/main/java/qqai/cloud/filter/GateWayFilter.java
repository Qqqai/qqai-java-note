package qqai.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author qqai
 * @createTime 2020/11/28 16:16
 * @description：标记 全局过滤器
 */

@Configuration
@Slf4j
public class GateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 必须携带的第一个参数为username
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (StringUtils.isEmpty(username)) {
            log.error("用户名非法，请求不被接受... /(ㄒoㄒ)/~~");
            // 设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 请求直接返回
            return exchange.getResponse().setComplete();
        }
        // 合法用户进入下一个过滤连
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
