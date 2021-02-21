package com.jeffrey.distributed.lock.order.dao;

import com.jeffrey.distributed.lock.order.entity.TblOrderLock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblOrderLockDao {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TblOrderLock record);

    int insertSelective(TblOrderLock record);

    TblOrderLock selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TblOrderLock record);

    int updateByPrimaryKey(TblOrderLock record);
}
