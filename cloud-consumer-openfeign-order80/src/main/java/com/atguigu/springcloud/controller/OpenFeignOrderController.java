package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.rpc.OpenFeignPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author czy
 * @Date 2020/7/28 3:00 下午
 * @Version 1.0
 */
@RestController
public class OpenFeignOrderController {

    @Autowired
    private OpenFeignPaymentService openFeignPaymentService;

    @GetMapping("/consumer/openfeign/{id}")
    public String getPaymentById(@PathVariable("id") String id) {
        return openFeignPaymentService.getById(id);
    }

    @GetMapping("/consumer/openfeign/timeout/test")
    public String timeout() {
        return openFeignPaymentService.timeout();
    }
}
