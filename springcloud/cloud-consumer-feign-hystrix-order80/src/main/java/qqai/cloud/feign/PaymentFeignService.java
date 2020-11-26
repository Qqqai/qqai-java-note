package qqai.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import qqai.cloud.common.R;
import qqai.cloud.feign.impl.PaymentFeignServiceImpl;

/**
 * @author qqai
 * @createTime 2020/11/23 14:03
 * @description：调用接口
 */

@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix/ok/{id}")
    R paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/time/out/{id}")
    R paymentInfo_TIME_OUT(@PathVariable("id") Integer id);
}
