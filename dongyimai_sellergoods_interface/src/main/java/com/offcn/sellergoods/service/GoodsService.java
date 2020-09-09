package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.group.Goods;
import com.offcn.pojo.TbGoods;
import com.offcn.pojo.TbItem;

import java.util.List;

public interface GoodsService {

	//findAll
	public List<TbGoods> findAll();

	//findPage
	public PageResult findPage(int pageNum, int pageSize);

	//add（已修改，需求：新增保存商品SPU表的同时，保存对应的扩展表、SKU表）（复合实体类Goods）
	public void add(Goods goods);
	
	//update（已修改，需求：修改保存商品SPU表的同时，保存对应的扩展表、SKU表）（复合实体类Goods）
	public void update(Goods goods);
	
	//findOne（已修改，需求：根据商品ID，查询SPU信息、附带扩展表信息、SKU表信息）（复合实体类Goods）
	public Goods findOne(Long id);

	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize);

	/**
	 * 批量审核商品
	 * @param ids
	 * @param status
	 */
	public void updateStatus(Long[] ids,String status);

	/**
	 * 通过商品ID和状态查询SKU集合
	 * @param ids
	 * @param status
	 * @return
	 */
	public List<TbItem> findItemListByGoodsIdsAndStatus(Long[] ids, String status);
	
}
