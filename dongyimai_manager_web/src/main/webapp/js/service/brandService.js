//品牌服务层
app.service('brandService',function($http){

	//findOne
	this.findOne=function(id){
		return $http.get('../brand/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../brand/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../brand/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../brand/delete.do?ids='+ids);
	}

	//模糊、条件查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../brand/search.do?page='+page+"&rows="+rows, searchEntity);
	}

	//模板管理模块 查询品牌下拉列表
	this.selectOptionList = function(){
		return $http.get('../brand/selectOptionList.do');
	}

});