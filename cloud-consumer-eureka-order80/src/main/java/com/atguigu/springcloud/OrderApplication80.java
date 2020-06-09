package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/23 0:39
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "",configuration = MySelfRule.class)
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class,args);
    }
}
