package com.jeffrey.distributed.lock.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/zk/grab")
@Slf4j
public class ZkGrabOrderController {

//    @Autowired
//    private CuratorFramework curatorFramework;
//
//    private String lockPath = "/lock/test/";
//
//
//    @Autowired
//    OrderService orderService;
//
//
//    @GetMapping("/do/{orderId}")
//    public String grab(@PathVariable("orderId") int orderId, int driverId){
//        String lockName = lockPath + orderId;
//        //TODO 获取分布式锁
//        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex (curatorFramework, lockName);
//        try{
//            //获取锁资源
//            boolean flag = lock.acquire(10, TimeUnit.HOURS);
//            if(flag){
//                System.out.println("司机:"+driverId+" 执行抢单逻辑");
//
//                boolean b = orderService.grab(orderId, driverId);
//                if(b) {
//                    System.out.println("司机:"+driverId+" 抢单成功");
//                }else {
//                    System.out.println("司机:"+driverId+" 抢单失败");
//                }
//            }
//        }catch (Exception e){
//            log.info("错误信息：{}",e.getMessage());
//        }finally {
//            try {
//                lock.release();
//                log.info("=========lockName:{}==============={}释放了锁",lockName,Thread.currentThread().getName());
//            } catch (Exception e) {
//                log.info("错误信息：{}",e.getMessage());
//            }
//        }
//        return "";
//    }
}
