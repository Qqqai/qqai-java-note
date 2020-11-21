package qqai.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import qqai.cloud.common.R;
import qqai.cloud.entity.Payment;

/**
 * @author qqai
 * @createTime 2020/11/17 22:29
 * @description：order
 */

@Slf4j
@RestController
@RequestMapping("/payment")
public class OrderController {

    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/consumer/creat")
    public R creat(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/creat", payment, R.class);
    }

    @GetMapping("/consumer/get/{id}")
    public R getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, R.class);
    }
}
