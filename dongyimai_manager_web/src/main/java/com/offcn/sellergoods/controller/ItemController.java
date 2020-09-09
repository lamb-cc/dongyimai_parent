package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbItem;
import com.offcn.sellergoods.service.ItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Reference(timeout = 30000)
	private ItemService itemService;
	

	//findAll
	@RequestMapping("/findAll")
	public List<TbItem> findAll(){			
		return itemService.findAll();
	}
	
	//findPage
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return itemService.findPage(page, rows);
	}
	
	//add
	@RequestMapping("/add")
	public Result add(@RequestBody TbItem item){
		try {
			itemService.add(item);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update
	@RequestMapping("/update")
	public Result update(@RequestBody TbItem item){
		try {
			itemService.update(item);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	

	//findOne
	@RequestMapping("/findOne")
	public TbItem findOne(Long id){
		return itemService.findOne(id);		
	}
	
	//delete
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			itemService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbItem item, int page, int rows  ){
		return itemService.findPage(item, page, rows);		
	}
	
}
