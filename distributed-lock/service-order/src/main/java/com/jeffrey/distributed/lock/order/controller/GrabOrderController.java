package com.jeffrey.distributed.lock.order.controller;


import com.jeffrey.distributed.lock.order.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 马士兵教育：晁鹏飞
 */
@RestController
@RequestMapping("/grab")
public class GrabOrderController {

    // 无锁
    @Autowired
//    @Qualifier("grabNoLockService")
    // jvm锁
//    @Qualifier("grabJvmLockService")
    // mysql锁
//    @Qualifier("grabMysqlLockService")
    // 手写redis
//    @Qualifier("grabRedisLockService")
    //单个redisson
//    @Qualifier("grabRedisRedissonService")
    // 红锁
    @Qualifier("grabRedisRedissonRedLockLockService")
    // cloud 锁
//    @Qualifier("cloudService")
    private GrabService grabService;

    @GetMapping("/do/{orderId}")
    public String grabMysql(@PathVariable("orderId") int orderId, int driverId){
        System.out.println("order:"+orderId+",driverId:"+driverId);
        grabService.grabOrder(orderId,driverId);
        return "";
    }

    //单个redisson
    @Autowired
    @Qualifier("grabRedisRedissonService")
    private GrabService redisGrabService;


    @GetMapping("/do-redis/{orderId}")
    public String grabRedis(@PathVariable("orderId") int orderId, int driverId){
        System.out.println("order:"+orderId+",driverId:"+driverId);
        redisGrabService.grabOrder(orderId,driverId);
        return "";
    }
}
