package com.atguigu.springcloud.controller;

import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/23 0:40
 */
@RestController
@Slf4j
public class OrderController {

    public static  final  String URL = "http://CLOUD-PROVIDER-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    public String get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(URL+"/payment/get/" + id, String.class);
    }

    @PostMapping("/consumer/create")
    public CommonResult add(@RequestBody Payment payment) {
        return  restTemplate.postForObject(URL+"/add",payment,CommonResult.class);
    }
}
