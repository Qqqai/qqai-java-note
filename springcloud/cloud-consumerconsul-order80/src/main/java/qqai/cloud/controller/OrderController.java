package qqai.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import qqai.cloud.common.R;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/11/22 14:27
 * @description：
 */

@RestController
@RequestMapping("/order/consumer")
public class OrderController {

    private static String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consul/port")
    public R getServerPort() {
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-provider-payment");
        ServiceInstance instance = instances.get(0);
//        System.out.println(instance.getHost());
        INVOKE_URL = "http://" + instance.getHost() + ":" + instance.getPort();
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul/port", R.class);
    }
}
