package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.sellergoods.service.SpecificationOptionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specificationOption")
public class SpecificationOptionController {

	@Reference(timeout = 30000)
	private SpecificationOptionService specificationOptionService;


	//findAll
	@RequestMapping("/findAll")
	public List<TbSpecificationOption> findAll(){			
		return specificationOptionService.findAll();
	}

	//findPage
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return specificationOptionService.findPage(page, rows);
	}
	
	//add
	@RequestMapping("/add")
	public Result add(@RequestBody TbSpecificationOption specificationOption){
		try {
			specificationOptionService.add(specificationOption);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	//update
	@RequestMapping("/update")
	public Result update(@RequestBody TbSpecificationOption specificationOption){
		try {
			specificationOptionService.update(specificationOption);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	//findOne
	@RequestMapping("/findOne")
	public TbSpecificationOption findOne(Long id){
		return specificationOptionService.findOne(id);		
	}
	
	//delete
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			specificationOptionService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//条件、模糊查询+分页
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbSpecificationOption specificationOption, int page, int rows  ){
		return specificationOptionService.findPage(specificationOption, page, rows);		
	}
	
}