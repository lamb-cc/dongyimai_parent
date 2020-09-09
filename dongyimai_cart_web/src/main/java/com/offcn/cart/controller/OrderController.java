package com.offcn.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.order.service.OrderService;
import com.offcn.pojo.TbOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	private OrderService orderService;
	
	//findAll
	@RequestMapping("/findAll")
	public List<TbOrder> findAll(){
		return orderService.findAll();
	}
	
	//findPage
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return orderService.findPage(page, rows);
	}
	
	//add（已修改，需求：在预下单之后，①保存订单、订单详情、清空redis缓存中的购物车。②保存支付日志到缓存、保护支付日志到数据库）
	@RequestMapping("/add")
	public Result add(@RequestBody TbOrder order){
		//获取当前登录人
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		order.setUserId(userId);
		//设置订单来源
		order.setSourceType("2");
		try {
			orderService.add(order);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update
	@RequestMapping("/update")
	public Result update(@RequestBody TbOrder order){
		try {
			orderService.update(order);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	//findOne
	@RequestMapping("/findOne")
	public TbOrder findOne(Long orderId){
		return orderService.findOne(orderId);		
	}
	
	//delete
	@RequestMapping("/delete")
	public Result delete(Long [] orderIds){
		try {
			orderService.delete(orderIds);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbOrder order, int page, int rows  ){
		return orderService.findPage(order, page, rows);		
	}


}