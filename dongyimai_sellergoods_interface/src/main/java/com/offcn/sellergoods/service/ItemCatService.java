package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

	//findAll
	public List<TbItemCat> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbItemCat item_cat);
	
	//update
	public void update(TbItemCat item_cat);
	
	//findOne
	public TbItemCat findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbItemCat item_cat, int pageNum, int pageSize);


	/**
	 * 通过父级ID查询本级SPU商品分类列表
	 * @param parentId	父级ID
	 * @return
	 */
	public List<TbItemCat> findByParentId(Long parentId);
	
}
