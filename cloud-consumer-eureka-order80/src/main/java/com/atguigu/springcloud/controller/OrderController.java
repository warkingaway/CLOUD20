package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.lb.LoadBalancer;
import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/23 0:40
 */
@RestController
@Slf4j
public class OrderController {

    public static  final  String URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/{id}")
    public String get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(URL+"/payment/get/" + id, String.class);
    }

    @PostMapping("/consumer/create")
    public CommonResult add(@RequestBody Payment payment) {
        return  restTemplate.postForObject(URL+"/add",payment,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(ObjectUtils.isEmpty(instances)){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
