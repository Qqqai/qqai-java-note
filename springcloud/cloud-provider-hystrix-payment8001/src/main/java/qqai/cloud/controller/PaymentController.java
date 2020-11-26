package qqai.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqai.cloud.common.R;
import qqai.cloud.service.PaymentService;

/**
 * @author qqai
 * @createTime 2020/11/23 13:29
 * @description：
 */

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/hystrix/ok/{id}")
    public R paymentInfo_OK(@PathVariable("id") Integer id) {
        String s = paymentService.paymentInfo_OK(id);
        log.info(s);
        return R.ok().put("ok", s);
    }

    @GetMapping("/hystrix/time/out/{id}")
    public R paymentInfo_TIME_OUT(@PathVariable("id") Integer id) {
        String s = null;
        try {
            s = paymentService.paymentInfo_TIME_OUT(id);
        } catch (InterruptedException e) {
            return R.error("出现错误");
        }
        log.info(s);
        return R.ok().put("time_out", s);
    }


    @GetMapping("/hystrix/payment/circuit/breaker/{id}")
    public R paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return R.ok().put("ok", s);
    }
}
