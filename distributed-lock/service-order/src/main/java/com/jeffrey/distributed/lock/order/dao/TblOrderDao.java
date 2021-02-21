package com.jeffrey.distributed.lock.order.dao;

import com.jeffrey.distributed.lock.order.entity.TblOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblOrderDao {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    TblOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);
}
