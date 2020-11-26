package qqai.cloud.lb.impl;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Configuration;
import qqai.cloud.lb.LoadBalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qqai
 * @createTime 2020/11/22 23:09
 * @description：负载均衡实现
 */

/**
 * 负载均衡算法实现
 */
@Configuration
public class LoadBalancerImpl implements LoadBalancer {

    // 原子操作的对象
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        // 上一次请求次数
        int current;
        // 当前请求
        int next;
        do {
            // 获取上一次请求完成后的结果值
            current = this.atomicInteger.get();
            // 设置本次请求
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            // 循环 如果交换出现错误 就表示没有成功 再次循环
        } while (!this.atomicInteger.compareAndSet(current, next));
        // 打印
        System.out.println("----next--->" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 获取到服务列表并且用当此请求的next % 服务的实例个数
        int i = getAndIncrement() % serviceInstances.size();
        // 使用这个实例完成本次请求
        return serviceInstances.get(i);
    }
}
