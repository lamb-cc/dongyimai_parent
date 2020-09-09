package com.offcn.order.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbOrder;
import com.offcn.pojo.TbPayLog;

import java.util.List;

public interface OrderService {

	//findAll
	public List<TbOrder> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbOrder order);
	
	//update
	public void update(TbOrder order);
	
	//findOne
	public TbOrder findOne(Long orderId);
	
	//delete
	public void delete(Long[] orderIds);

	//条件、模糊查询+分页
	public PageResult findPage(TbOrder order, int pageNum, int pageSize);


	/**
	 * 通过用户ID在缓存中读取支付日志
	 * @param userId
	 * @return
	 */
	public TbPayLog findPayLogFromRedis(String userId);

	/**
	 * 付款成功后，修改订单支付状态
	 * @param out_trade_no     订单编号
	 * @param trade_no			支付宝的交易流水号
	 */
	public void updateOrderStatus(String out_trade_no, String trade_no);


}