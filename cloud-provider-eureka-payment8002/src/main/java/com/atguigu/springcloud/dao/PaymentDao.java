package com.atguigu.springcloud.dao;

import com.atguigui.springcloud.model.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/20 1:18
 */
@Mapper
public interface PaymentDao {

    int add(Payment payment);

    Payment getById(@Param("id") String id);
}
