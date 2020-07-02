package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author czy
 * @Date 2020/7/2 12:04 下午
 * @Version 1.0
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //坐标
    private final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        //第一个参数是期望值，第二个参数是修改值是
        System.out.println("*******第几次访问，次数next: " + next);
        return next;
    }

    /**
     * 收集服务器总共有多少台能够提供服务的机器，并放到list里面
     *
     * @param serviceInstances
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //得到机器的列表
        int index = getAndIncrement() % serviceInstances.size();
        //得到服务器的下标位置
        return serviceInstances.get(index);
    }
}
