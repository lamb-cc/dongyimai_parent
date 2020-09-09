package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbSeller;

import java.util.List;

public interface SellerService {

	//findAll
	public List<TbSeller> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbSeller seller);
	
	//update
	public void update(TbSeller seller);
	
	//findOne
	public TbSeller findOne(String sellerId);

	//delete
	public void delete(String[] sellerIds);

	//条件、模糊查询+分页
	public PageResult findPage(TbSeller seller, int pageNum, int pageSize);

	/**
	 * 商家审核
	 * @param sellerId  商家ID
	 * @param status	审核状态
	 */
	public void updateStatus(String sellerId, String status);
	
}