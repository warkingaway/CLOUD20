package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @Author czy
 * @Date 2020/8/1 11:45 上午
 * @Version 1.0
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        Integer num = 5;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut, id:" + id + " 耗时：" + num;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "服务繁忙～请稍后再试";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_handler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  // 时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")   //失败率达到多少 断路
    })
    public String paymentCircuitBreaker(Integer id) {
        Assert.isTrue(id > 0, "***********id不能为负数");
        return Thread.currentThread().getName() + " 调用成功，流水号：" + IdUtil.simpleUUID();
    }

    public String paymentCircuitBreaker_handler(Integer id) {
        return "id不能为负数，请重新输入";
    }

}
