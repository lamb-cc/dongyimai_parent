package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbItem;

import java.util.List;

public interface ItemService {

	//findAll
	public List<TbItem> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbItem item);
	
	//update
	public void update(TbItem item);
	
	//findOne
	public TbItem findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbItem item, int pageNum, int pageSize);
	
}
