package com.jeffrey.distributed.transactional.service;

import com.jeffrey.distributed.transactional.dao.TblOneDao;
import com.jeffrey.distributed.transactional.dao.TblOrderDao;
import com.jeffrey.distributed.transactional.entity.TblOne;
import com.jeffrey.distributed.transactional.entity.TblOrder;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


/**
 * @author yueyi2019
 */
@Service
public class OrderService {

	@Autowired
	TblOneDao mapper;

	@Autowired
	TblOrderDao tblOrderDao;


	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	public String addOrder() {


		TblOrder tblOrder = new TblOrder();
		tblOrder.setOrderId(1);
		tblOrder.setBuyer("马士兵");

		tblOrderDao.insert(tblOrder);

		restTemplate.getForEntity("http://inventory/reduce?goodId=1", null);
		System.out.println(1/0);

		return "";
	}


	public String rm2Update() {
		rm2UpdateRemote();

		TblOne o = new TblOne();
		o.setId(2);
		o.setName("rm2");
		mapper.insertSelective(o);
//		int i = 1/0;
		return "";
	}


	@Autowired
	private RestTemplate restTemplate;

	private void rm2() {
		restTemplate.getForEntity("http://two/rm2", null);
	}

	private void rm2UpdateRemote() {
		restTemplate.getForEntity("http://two/rm2-update", null);
	}

	private void rm3() {
		restTemplate.getForEntity("http://three/rm3", null);
	}
}
