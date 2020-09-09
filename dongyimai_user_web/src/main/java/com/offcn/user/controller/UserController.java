package com.offcn.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbUser;
import com.offcn.user.service.UserService;
import com.offcn.util.PhoneFormatCheckUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Reference
	private UserService userService;
	

	//dinfAll
	@RequestMapping("/findAll")
	public List<TbUser> findAll(){
		return userService.findAll();
	}
	
	//findPage
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return userService.findPage(page, rows);
	}
	
	//add（已修改，需求：在新增用户之前，比对验证码）
	@RequestMapping("/add")
	public Result add(@RequestBody TbUser user, String smsCode){
		//比对验证码（缓存中的验证码与用户输入的验证码做比对）
		if(!userService.checkSmsCode(user.getPhone(),smsCode)){
			return new Result(false,"验证码错误！");
		}
		try {
			userService.add(user);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update
	@RequestMapping("/update")
	public Result update(@RequestBody TbUser user){
		try {
			userService.update(user);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	//findOne
	@RequestMapping("/findOne")
	public TbUser findOne(Long id){
		return userService.findOne(id);		
	}
	
	//delete
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			userService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbUser user, int page, int rows  ){
		return userService.findPage(user, page, rows);		
	}

	/**
	 * 生成验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/createSmsCode")
	public Result createSmsCode(String phone){
		//判断手机号格式验证（前端验证码判空，后端验证判断格式）
		if(!PhoneFormatCheckUtils.isPhoneLegal(phone)){
			return new Result(false,"手机格式验证失败！");
		}
		try {
			userService.createSmsCode(phone);
			return new Result(true,"短信发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"短信发送失败");
		}
	}
	
}
