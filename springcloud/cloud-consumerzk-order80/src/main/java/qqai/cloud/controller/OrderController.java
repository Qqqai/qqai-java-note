package qqai.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import qqai.cloud.common.R;

import javax.annotation.Resource;

/**
 * @author qqai
 * @createTime 2020/11/22 14:27
 * @description：
 */

@RestController
@RequestMapping("/order/comsumer")
public class OrderController {

    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/zk/port")
    public R getServerPort() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/port", R.class);
    }
}
