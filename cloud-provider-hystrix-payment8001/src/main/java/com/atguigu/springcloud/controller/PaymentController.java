package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author czy
 * @Date 2020/8/1 11:51 上午
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/hystrix/payment/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        String s = paymentService.paymentInfo_OK(id);
        log.info(s);
        return s;
    }

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        String s = paymentService.paymentInfo_TimeOut(id);
        log.info(s);
        return s;
    }

    // 服务熔断
    @GetMapping("/hystrix/payment/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id) {
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return s;
    }


}
