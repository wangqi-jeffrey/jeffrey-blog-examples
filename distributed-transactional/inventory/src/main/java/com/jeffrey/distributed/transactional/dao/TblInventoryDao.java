package com.jeffrey.distributed.transactional.dao;

import com.jeffrey.distributed.transactional.entity.TblInventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblInventoryDao {
    int deleteByPrimaryKey(Integer goodId);

    int insert(TblInventory record);

    int insertSelective(TblInventory record);

    TblInventory selectByPrimaryKey(Integer goodId);

    int updateByPrimaryKeySelective(TblInventory record);

    int updateByPrimaryKey(TblInventory record);
}
