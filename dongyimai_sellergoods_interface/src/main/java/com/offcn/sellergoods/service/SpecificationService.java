package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.group.Specification;
import com.offcn.pojo.TbSpecification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

	//findAll
	public List<TbSpecification> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add（已修改，需求：新增规格名称的同时，要新增对应的规格选项）（复合实体类Specification）
	public void add(Specification specification);

	//update（已修改，需求：修改规格名称的同时，要修改对应的规格选项）（复合实体类Specification）
	public void update(Specification specification);

	//findOne（已修改，需求：根据ID查询规格名称时，也要同时查询对应的规格选项）（复合实体类Specification）
	public Specification findOne(Long id);

	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize);

	/**
	 * 查询规格下拉列表
	 * @return
	 */
	public List<Map> selectOptionList();
	
}
