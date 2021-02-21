package com.jeffrey.distributed.lock.order.service.impl;

import com.jeffrey.distributed.lock.order.entity.TblOrderLock;
import com.jeffrey.distributed.lock.order.lock.MysqlLock;
import com.jeffrey.distributed.lock.order.service.GrabService;
import com.jeffrey.distributed.lock.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grabMysqlLockService")
public class GrabMysqlLockServiceImpl implements GrabService {

    @Autowired
    private MysqlLock lock;


    @Autowired
    OrderService orderService;

    ThreadLocal<TblOrderLock> orderLock = new ThreadLocal<>();

    @Override
    public String grabOrder(int orderId, int driverId) {
        //生成key
        TblOrderLock ol = new TblOrderLock();
        ol.setOrderId(orderId);
        ol.setDriverId(driverId);

        orderLock.set(ol);
        lock.setOrderLockThreadLocal(orderLock);

        // lock
        lock.lock();

        // 执行业务
        try {
            System.out.println("司机:"+driverId+" 执行抢单逻辑");

            boolean b = orderService.grab(orderId, driverId);
            if(b) {
                System.out.println("司机:"+driverId+" 抢单成功");
            }else {
                System.out.println("司机:"+driverId+" 抢单失败");
            }
        }finally {
            // 释放锁
            lock.unlock();
        }

        // 执行业务

        return null;
    }
}
