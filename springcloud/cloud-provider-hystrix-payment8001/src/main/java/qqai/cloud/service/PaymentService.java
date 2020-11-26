package qqai.cloud.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import qqai.cloud.common.R;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/11/23 13:22
 * @description：
 */

@Service
public class PaymentService {

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:   " + Thread.currentThread().getName() + "  paymentInfo_OK, id:" + id + "(●'◡'●)";
    }

    /**
     * 超时
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TIME_OUT_Handler", commandProperties = {
            // 此处标注 此线程的超时时间是三秒钟
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TIME_OUT(Integer id) throws InterruptedException {
        int time = 2;
        TimeUnit.SECONDS.sleep(time);
        // 模拟异常
//        int i = 10 / 0;
        return "线程池:   " + Thread.currentThread().getName() + "  paymentInfo_TIME_OUT, id:" + id + "(●'◡'●)，";// 耗时" + time + "s";
    }

    // hystrix的兜底方法
    public String paymentInfo_TIME_OUT_Handler(Integer id) {
        return "线程池:   " + Thread.currentThread().getName() + "  paymentInfo_TIME_OUT_Handler, id:" + id + "/(ㄒoㄒ)/~~，超时返回兜底方法";
    }


    //=========================熔断


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为小于0的数");
        }
        String serialNumber = IdUtil.simpleUUID();
        String serialNumber1 = UUID.randomUUID().toString().replaceAll("_", "");
        return Thread.currentThread().getName() + "\t" + "调用成功， 流水号:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "调用失败";
    }
}
