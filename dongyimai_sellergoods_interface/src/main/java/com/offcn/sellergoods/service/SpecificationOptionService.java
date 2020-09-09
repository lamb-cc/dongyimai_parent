package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbSpecificationOption;

import java.util.List;

public interface SpecificationOptionService {

	//findALL
	public List<TbSpecificationOption> findAll();

	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbSpecificationOption specification_option);
	
	//update
	public void update(TbSpecificationOption specification_option);
	
	//findOne
	public TbSpecificationOption findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbSpecificationOption specification_option, int pageNum, int pageSize);
	
}