package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author czy
 * @Date 2020/7/30 4:48 下午
 * @Version 1.0
 */
@Configuration
public class FeignConfig {
    /**
     * feign日志的四个级别
     * NONE     No logging (DEFAULT).
     * BASIC    仅记录请求方法和URL以及响应状态代码和执行时间
     * HEADERS  记录基本信息以及请求和响应标头
     * FULL     记录请求和响应体，正文和元数据
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
