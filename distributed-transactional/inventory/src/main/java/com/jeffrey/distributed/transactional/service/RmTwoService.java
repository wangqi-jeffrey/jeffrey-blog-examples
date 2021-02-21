package com.jeffrey.distributed.transactional.service;

import com.jeffrey.distributed.transactional.dao.TblInventoryDao;
import com.jeffrey.distributed.transactional.dao.TblTwoDao;
import com.jeffrey.distributed.transactional.entity.TblInventory;
import com.jeffrey.distributed.transactional.entity.TblTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


/**
 * @author yueyi2019
 */
@Service
public class RmTwoService {

	@Autowired
	TblTwoDao mapper;

	@Autowired
	TblInventoryDao tblInventoryDao;

	@Transactional
	public String reduce(int goodId) {
		TblInventory tblInventory = tblInventoryDao.selectByPrimaryKey(goodId);
		tblInventory.setNum(tblInventory.getNum()-1);

		tblInventoryDao.updateByPrimaryKey(tblInventory);

		return "";
	}

	public String updateRm2() {
		TblTwo o = mapper.selectByPrimaryKey(2);
		o.setName(o.getName()+new Random().nextInt(2));
		mapper.updateByPrimaryKeySelective(o);
		return "";
	}
}
