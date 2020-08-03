package com.atguigu.springcloud.rpc;

import com.atguigu.springcloud.fallback.PaymentFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author czy
 * @Date 2020/8/1 12:43 下午
 * @Version 1.0
 */
@FeignClient(value = "cloud-payment-hystrix-service",fallback = PaymentFallback.class)
public interface PaymentHystrixService {

    @GetMapping("/hystrix/payment/ok/{id}")
    String paymentInfoOK(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/payment/timeout/{id}")
    String paymentInfoTimeOut(@PathVariable("id") Integer id);

}
