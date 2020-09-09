package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {

	//findAll
	public List<TbTypeTemplate> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbTypeTemplate type_template);
	
	//update
	public void update(TbTypeTemplate type_template);
	
	//findOne
	public TbTypeTemplate findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbTypeTemplate type_template, int pageNum, int pageSize);

	/**
	 * 根据模板ID查询规格及规格选项列表
	 * @param typeId
	 * @return
	 */
	public List<Map>  findSpecList(Long typeId);

}