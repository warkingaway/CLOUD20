package com.atguigu.springcloud.fallback;

import com.atguigu.springcloud.rpc.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @Author czy
 * @Date 2020/8/1 1:36 下午
 * @Version 1.0
 */
@Component
public class PaymentFallback implements PaymentHystrixService {

    @Override
    public String paymentInfoOK(Integer id) {
        return " 全局处理 ok";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return " 全局处理 消费者端服务降级，服务繁忙～请稍后";
    }
}
