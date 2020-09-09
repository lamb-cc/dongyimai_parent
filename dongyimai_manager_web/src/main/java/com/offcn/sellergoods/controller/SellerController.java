package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbSeller;
import com.offcn.sellergoods.service.SellerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference(timeout = 30000)
	private SellerService sellerService;
	

	//findAll
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){			
		return sellerService.findAll();
	}
	
	//findPage
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return sellerService.findPage(page, rows);
	}
	
	//add
	@RequestMapping("/add")
	public Result add(@RequestBody TbSeller seller){
		try {
			sellerService.add(seller);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update
	@RequestMapping("/update")
	public Result update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	//findOne
	@RequestMapping("/findOne")
	public TbSeller findOne(String sellerId){
		return sellerService.findOne(sellerId);		
	}
	
	//delete
	@RequestMapping("/delete")
	public Result delete(String [] sellerIds){
		try {
			sellerService.delete(sellerIds);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbSeller seller, int page, int rows  ){
		return sellerService.findPage(seller, page, rows);		
	}

	//商家审核
	@RequestMapping("/updateStatus")
	public Result updateStatus(String sellerId,String status){
		try {
			sellerService.updateStatus(sellerId,status);
			return new Result(true,"审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"审核失败");
		}
	}
	
}