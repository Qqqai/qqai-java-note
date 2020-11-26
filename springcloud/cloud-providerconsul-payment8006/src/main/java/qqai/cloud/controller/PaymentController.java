package qqai.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqai.cloud.common.R;

import java.util.UUID;

/**
 * @author qqai
 * @createTime 2020/11/22 14:27
 * @description：
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/consul/port")
    public R getServerPort() {
        return R.ok().put("port", port + "----->" + UUID.randomUUID().toString());
    }
}
