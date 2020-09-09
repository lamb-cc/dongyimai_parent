package com.offcn.content.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {

	//findAll
	public List<TbContentCategory> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbContentCategory content_category);
	
	//update
	public void update(TbContentCategory content_category);
	
	//findOne
	public TbContentCategory findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbContentCategory content_category, int pageNum, int pageSize);
	
}
