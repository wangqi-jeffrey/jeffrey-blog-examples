package com.jeffrey.distributed.transactional.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
public class RmTwoInterfaceImpl implements InventoryInterface {

    @Override
    @Transactional
    public String inventoryTry(BusinessActionContext businessActionContext) {
        System.out.println("预备");
        System.out.println("inventory try");
//        System.out.println(1/0);

        return null;
    }

    @Override
    @Transactional
    public boolean inventoryCommit(BusinessActionContext businessActionContext) {
        System.out.println("提交");
        System.out.println("inventory confirm");
        return true;
    }

    @Override
    @Transactional
    public boolean inventoryRollback(BusinessActionContext businessActionContext) {
        System.out.println("回滚");
        System.out.println("inventory rollback");
        return true;
    }


    @Autowired
    private RestTemplate restTemplate;

    private void rm2() {
        restTemplate.getForEntity("http://two/rm2-tcc", null);
    }

    private void rm3() {
        restTemplate.getForEntity("http://three/rm3-tcc", null);
    }
}
