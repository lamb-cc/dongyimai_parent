package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbGoodsDesc;

import java.util.List;

public interface GoodsDescService {

	//findALL
	public List<TbGoodsDesc> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);

	//add
	public void add(TbGoodsDesc goods_desc);
	
	//update
	public void update(TbGoodsDesc goods_desc);
	
	//findOne
	public TbGoodsDesc findOne(Long goodsId);
	
	//delete
	public void delete(Long[] goodsIds);

	//条件、模糊查询+分页
	public PageResult findPage(TbGoodsDesc goods_desc, int pageNum, int pageSize);
	
}