package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.rpc.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author czy
 * @Date 2020/8/1 12:48 下午
 * @Version 1.0
 */
@RestController
@Slf4j
/*@DefaultProperties(defaultFallback = "")*/
public class OrderHystrixController {

    private PaymentHystrixService paymentHystrixService;

    @Autowired
    public void setPaymentHystrixService(PaymentHystrixService paymentHystrixService) {
        this.paymentHystrixService = paymentHystrixService;
    }

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        String s = paymentHystrixService.paymentInfoOK(id);
        log.info(s);
        return s;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeouthandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String timeout(@PathVariable("id") Integer id) {
        String s = paymentHystrixService.paymentInfoTimeOut(id);
        log.info(s);
        return s;
    }

    public String timeouthandler(Integer id){
        return " 支付系统繁忙，消费者端服务降级";
    }
}
