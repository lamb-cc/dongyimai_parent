package com.offcn.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.group.Goods;
import com.offcn.pojo.TbGoods;
import com.offcn.sellergoods.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference(timeout = 30000)
	private GoodsService goodsService;


	//findAll
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){			
		return goodsService.findAll();
	}

	//findPage
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodsService.findPage(page, rows);
	}
	
	//add（已修改，需求：新增保存商品SPU表的同时，保存对应的扩展表、SKU表）
	@RequestMapping("/add")
	public Result add(@RequestBody Goods goods){
		//获得登录人信息，保存商家信息
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.getGoods().setSellerId(sellerId);

		try {
			goodsService.add(goods);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update（需求：修改保存商品SPU表的同时，保存对应的扩展表、SKU表）
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods){
		//权限验证
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		//当前登录人和商品中的商家信息比对
		Goods goods1 = goodsService.findOne(goods.getGoods().getId());
		if(!goods1.getGoods().getSellerId().equals(sellerId)||!goods.getGoods().getSellerId().equals(sellerId)){
			return new Result(false,"非法操作");
		}
		try {
			goodsService.update(goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	//findOne（已修改，需求：根据商品ID，查询SPU信息、附带扩展表信息、SKU表信息）
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	//delete（已修改，需求：逻辑删除）
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页（已修改，详见实现类）
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoods goods, int page, int rows  ){
		//获得登录人信息
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		//设置登录人为查询条件
		goods.setSellerId(sellerId);

		return goodsService.findPage(goods, page, rows);		
	}
	
}