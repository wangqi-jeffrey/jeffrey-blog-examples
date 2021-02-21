package com.jeffrey.distributed.transactional.controller;

import com.jeffrey.distributed.transactional.service.OrderInterface;
import com.jeffrey.distributed.transactional.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService rmOneService;

    @GetMapping("/order-add")
    public String addOrder() throws InterruptedException {
        rmOneService.addOrder();
        return "success";
    }

    @Autowired
    private OrderInterface orderInterface;

    @GetMapping("/order-tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        orderInterface.orderTry(null);
        return "success";
    }

}
