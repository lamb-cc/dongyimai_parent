package com.offcn.user.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbAddress;

import java.util.List;

public interface AddressService {

	//findAll
	public List<TbAddress> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbAddress address);
	
	//update
	public void update(TbAddress address);
	
	//findOne
	public TbAddress findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbAddress address, int pageNum, int pageSize);

	/**
	 * 通过当前登陆人获得地址列表
	 * @param userId
	 * @return
	 */
	public List<TbAddress> findListByUserId(String userId);
	
}
