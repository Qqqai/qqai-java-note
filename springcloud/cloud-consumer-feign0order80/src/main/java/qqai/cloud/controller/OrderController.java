package qqai.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqai.cloud.common.R;
import qqai.cloud.feign.PaymentFeignService;

/**
 * @author qqai
 * @createTime 2020/11/23 00:24
 * @description：
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public R getPaymentById(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/time/out")
    public R PaymentTimeOut() {
        return paymentFeignService.paymentFeignTimeOut();
    }

}
