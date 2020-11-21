package qqai.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import qqai.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qqai.cloud.common.R;
import qqai.cloud.entity.Payment;

import java.util.Objects;

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
    /**
     * 服务对象
     */
    @Autowired
    private PaymentService paymentService;

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