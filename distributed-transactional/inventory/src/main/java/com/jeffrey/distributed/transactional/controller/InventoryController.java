package com.jeffrey.distributed.transactional.controller;

import com.jeffrey.distributed.transactional.service.InventoryInterface;
import com.jeffrey.distributed.transactional.service.RmTwoService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private RmTwoService rmTwoService;

    @Autowired
    private InventoryInterface inventoryInterface;

    @GetMapping("/reduce")
    public String reduce(Integer goodId){

        rmTwoService.reduce(goodId);
        return "success";
    }

    @GetMapping("/inventory-tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String twoTcc(){

        inventoryInterface.inventoryTry(null);
//        int i = 1/0;
        return "success";
    }
}
