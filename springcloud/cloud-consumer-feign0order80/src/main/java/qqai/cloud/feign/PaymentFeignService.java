package qqai.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import qqai.cloud.common.R;

/**
 * @author qqai
 * @createTime 2020/11/23 00:20
 * @description：feign接口 笔记 feign集成了ribbon 所以底层会自带一个负载均衡的机制，ribbon默认是轮询所以此处也是轮询的方式
 */

@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    R getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/time/out")
    R paymentFeignTimeOut();
}
