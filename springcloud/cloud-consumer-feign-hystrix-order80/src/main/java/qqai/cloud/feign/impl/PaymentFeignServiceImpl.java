package qqai.cloud.feign.impl;

import org.springframework.stereotype.Component;
import qqai.cloud.common.R;
import qqai.cloud.feign.PaymentFeignService;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author qqai
 * @createTime 2020/11/23 16:20
 * @description：fallback方法接口
 */

@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {

    @Override
    public R paymentInfo_OK(Integer id) {
        return R.error("方法超时");
    }

    @Override
    public R paymentInfo_TIME_OUT(Integer id) {
        return R.error("方法超时");
    }
}
