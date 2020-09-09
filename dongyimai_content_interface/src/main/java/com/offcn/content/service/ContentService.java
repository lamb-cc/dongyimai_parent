package com.offcn.content.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbContent;

import java.util.List;

public interface ContentService {

	//findAll
	public List<TbContent> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbContent content);
	
	//updata
	public void update(TbContent content);
	
	//findOne
	public TbContent findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbContent content, int pageNum, int pageSize);

	/**
	 * 通过广告分类ID查询本类广告列表（网站前台→首页广告轮播）
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> findContentByCategoryId(Long categoryId);
	
}
