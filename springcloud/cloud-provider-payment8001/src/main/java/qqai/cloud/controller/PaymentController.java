package qqai.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import qqai.cloud.common.R;
import qqai.cloud.entity.Payment;
import qqai.cloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Payment)表控制层
 *
 * @author qqai
 * @since 2020-11-15 01:18:20
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;
    /**
     * 服务对象
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * 获取注册中心的信息...
     *
     * @return
     */
    @GetMapping("/discovery")
    public R discovery() {
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        List<String> list = instances.stream().map(item -> item.getHost() + "\t" + item.getPort() + "\t" + item.getPort() + "\t" + item.getUri()).collect(Collectors.toList());
        Map<String, List<String>> map = new HashMap<>();
        map.put("services", services);
        map.put("instanceInfo", list);
        return Objects.requireNonNull(R.ok().put("info", map)).put("discovery", discoveryClient);
    }

    /**
     * 向数据库添加一条记录
     *
     * @param payment 添加实体
     * @return
     */
    @PostMapping("/creat")
    public R create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        log.info("插入结果---- --> serverPort -- " + serverPort + "--->" + result);
        if (result > 0) {
            return R.ok("数据插入成功").put("serverPort", serverPort);
        } else {
            return R.error("插入失败").put("serverPort", serverPort);
        }
    }

    @GetMapping("/get/{id}")
    public R getPaymentById(@PathVariable Long id) {
        Payment p = paymentService.queryById(id);
        if (p != null) {
            log.info("serverPort---->" + serverPort);
            return Objects.requireNonNull(R.ok().put("payment", p)).put("serverPort", serverPort);
        } else {
            return R.error("无对应记录").put("serverPort", serverPort);
        }
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return
     */
    @GetMapping("selectOne")
    public R selectOne(Long id) {
        Payment payment = this.paymentService.queryById(id);
        return R.ok().put("payment", payment);
    }

}