package qqai.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqai.cloud.common.R;
import qqai.cloud.feign.PaymentFeignService;

/**
 * @author qqai
 * @createTime 2020/11/23 14:05
 * @description：
 */

@RestController
@RequestMapping("/order")
// 标明全局异常回滚的方法
@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/hystrix/ok/{id}")
    R paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentFeignService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/time/out/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TIME_OUT_FallBackMethod", commandProperties = {
//            // 此处标注 此线程的超时时间是三秒钟
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    R paymentInfo_TIME_OUT(@PathVariable("id") Integer id) {
        return paymentFeignService.paymentInfo_TIME_OUT(id);
    }

    // 独享fallback
    public R paymentInfo_TIME_OUT_FallBackMethod(@PathVariable("id") Integer id) {
        return R.ok().put("msg", "服务端降级，返回");
    }

    // 全局fallback
    public R globalFallBack() {
        return R.error("服务异常请稍后再试");
    }
}
