package qqai.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import qqai.cloud.common.R;
import qqai.cloud.entity.Payment;
import qqai.cloud.lb.LoadBalancer;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @PostMapping("/consumer/creat")
    public R creat(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/creat", payment, R.class);
    }

    @GetMapping("/consumer/get/{id}")
    public R getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, R.class);
    }

    @GetMapping("/consumer/get/entity/{id}")
    public R getPaymentEntity(@PathVariable Long id) {
        ResponseEntity<R> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, R.class);
        return entity.getStatusCode().is2xxSuccessful() ? entity.getBody() : R.error("错误");
    }

    @GetMapping("/consumer/get/LB/{id}")
    public R getPaymentLB(@PathVariable Long id) {
        // 获取全部的可用服务对象
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() < 1) {
            return R.error("无可用服务");
        }
        // 使用自己写的负载均衡算法获取使用的实例对象
        ServiceInstance instance = loadBalancer.instances(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/get/" + id, R.class);
    }
}
