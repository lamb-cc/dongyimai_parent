package com.offcn.user.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;

import java.util.List;

public interface UserService {

	//findAll
	public List<TbUser> findAll();
	
	//findPage
	public PageResult findPage(int pageNum, int pageSize);
	
	//add
	public void add(TbUser user);
	
	//update
	public void update(TbUser user);
	
	//findOne
	public TbUser findOne(Long id);
	
	//delete
	public void delete(Long[] ids);

	//条件、模糊查询+分页
	public PageResult findPage(TbUser user, int pageNum, int pageSize);

	/**
	 * 生成短信验证码
	 * @param phone
	 */
	public void createSmsCode(String phone);

	/**
	 * 比对短信验证码
	 * @param phone
	 * @param smsCode
	 * @return
	 */
	public boolean checkSmsCode(String phone, String smsCode);
	
}
