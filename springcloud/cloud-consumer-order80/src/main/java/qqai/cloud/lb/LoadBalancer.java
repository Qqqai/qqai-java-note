package qqai.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author qqai
 * @createTime 2020/11/22 23:08
 * @description：负载均衡
 */

public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
