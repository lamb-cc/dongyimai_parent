package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {

	//findAll
	public List<TbBrand> findAll();

	//findPage
	public PageResult findPage(int pageNum, int pageSize);

	//add
	public void add(TbBrand brand);

	//update
	public void update(TbBrand brand);

	//findOne
	public TbBrand findOne(Long id);

	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize);

	/**
	 * 模板管理模块 查询品牌下拉列表
	 * @return
	 */
	public List<Map> selectOptionList();

}