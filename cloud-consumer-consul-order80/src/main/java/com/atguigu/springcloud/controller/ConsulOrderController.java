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
 * @date 2020/5/6 23:10
 */
@RestController
@Slf4j
public class ConsulOrderController {

    public static  final  String URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul")
    public String get() {
        return restTemplate.getForObject(URL+"/payment/consul" , String.class);
    }

    @PostMapping("/consumer/create")
    public CommonResult add(@RequestBody Payment payment) {
        return  restTemplate.postForObject(URL+"/add",payment,CommonResult.class);
    }




}
