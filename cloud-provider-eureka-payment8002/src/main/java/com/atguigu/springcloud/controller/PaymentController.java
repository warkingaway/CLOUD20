package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/23 0:27
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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
}
