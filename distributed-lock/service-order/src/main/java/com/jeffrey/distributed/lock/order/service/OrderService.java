package com.jeffrey.distributed.lock.order.service;

public interface OrderService {

	public boolean grab(int orderId, int driverId);

}
