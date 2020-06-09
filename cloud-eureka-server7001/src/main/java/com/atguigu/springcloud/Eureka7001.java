package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/27 22:29
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001.class,args);
    }
}
