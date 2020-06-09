package com.atguigu.springcloud.service;

import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/20 1:32
 */
public interface PaymentService {

    int add(Payment payment);

    CommonResult<Payment> getById(@Param("id") String id);


}
