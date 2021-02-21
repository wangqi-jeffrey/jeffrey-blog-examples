package com.jeffrey.distributed.lock.order.service;


/**
 * @author yueyi2019
 */
public interface GrabService {

    /**
     * 司机抢单
     * @param orderId
     * @param driverId
     * @return
     */
    public String grabOrder(int orderId , int driverId);
}
