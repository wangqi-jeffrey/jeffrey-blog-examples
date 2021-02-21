package com.jeffrey.distributed.transactional.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface InventoryInterface {

    @TwoPhaseBusinessAction(name = "inventoryTccAction" , commitMethod = "inventoryCommit" ,rollbackMethod = "inventoryRollback")
    public String inventoryTry(BusinessActionContext businessActionContext);

    public boolean inventoryCommit(BusinessActionContext businessActionContext);

    public boolean inventoryRollback(BusinessActionContext businessActionContext);
}
