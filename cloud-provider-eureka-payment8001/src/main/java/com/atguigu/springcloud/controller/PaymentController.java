package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/23 0:27
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/add")
    public CommonResult add (@RequestBody Payment payment){
        int rs = paymentService.add(payment);
        if(rs>0){
            return new CommonResult<>(200,"插入数据库成功 port:"+serverPort,rs);
        }else{
            return new CommonResult<>(400,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public String get (@PathVariable("id") String id){
        return  serverPort + "--" + paymentService.getById(id).toString();
    }

    @GetMapping("/discovery")
    private Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(s->{
            System.out.println("______________服务列表:"+s);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        instances.forEach(s->{
            System.out.println(s.getServiceId() +"--"+ s.getHost()+"--"+s.getPort()+"--"+s.getUri());
        });
        return this.discoveryClient;
    }
}
