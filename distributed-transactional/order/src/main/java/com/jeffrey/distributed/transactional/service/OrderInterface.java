package com.jeffrey.distributed.transactional.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderInterface {

    @TwoPhaseBusinessAction(name = "rm1TccAction" , commitMethod = "orderCommit" ,rollbackMethod = "orderRollback")
    public String orderTry(BusinessActionContext businessActionContext);

    public boolean orderCommit(BusinessActionContext businessActionContext);

    public boolean orderRollback(BusinessActionContext businessActionContext);
}
