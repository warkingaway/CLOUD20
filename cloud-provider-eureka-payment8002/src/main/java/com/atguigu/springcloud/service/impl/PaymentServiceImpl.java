package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.service.PaymentService;
import com.atguigui.springcloud.model.CommonResult;
import com.atguigui.springcloud.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author czy
 * @version 1.0
 * @date 2020/4/20 1:34
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public CommonResult<Payment> getById(String id) {
        return new CommonResult<>(200,"查询成功",paymentDao.getById(id));
    }
}
