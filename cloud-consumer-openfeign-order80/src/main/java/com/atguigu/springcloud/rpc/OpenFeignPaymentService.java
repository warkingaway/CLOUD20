package com.atguigu.springcloud.rpc;

import com.atguigu.springcloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author czy
 * @Date 2020/7/28 2:55 下午
 * @Version 1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",configuration = FeignConfig.class)
public interface OpenFeignPaymentService {

    @GetMapping("/payment/get/{id}")
    String getById(@PathVariable("id") String id);

    @GetMapping("/payment/timeout/test")
    String timeout();
}
