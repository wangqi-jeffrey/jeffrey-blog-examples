package com.jeffrey.distributed.transactional.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
public class RmOneInterfaceImpl implements OrderInterface {

    @Override
    @Transactional
    public String orderTry(BusinessActionContext businessActionContext) {
        // 查询是事务记录表，xxxx
        System.out.println("预备");
        System.out.println("order try");

        inventoryTcc();
        System.out.println(1/0);
        return null;
    }

    @Override
    @Transactional
    public boolean orderCommit(BusinessActionContext businessActionContext) {
        System.out.println("提交");
        System.out.println("order confirm");
        return true;
    }

    @Override
    @Transactional
    public boolean orderRollback(BusinessActionContext businessActionContext) {
        System.out.println("回滚");
        System.out.println("order rollback");
        return true;
    }


    @Autowired
    private RestTemplate restTemplate;

    private void inventoryTcc() {
        restTemplate.getForEntity("http://inventory/inventory-tcc", null);
    }

    private void rm3() {
        restTemplate.getForEntity("http://three/rm3-tcc", null);
    }
}
