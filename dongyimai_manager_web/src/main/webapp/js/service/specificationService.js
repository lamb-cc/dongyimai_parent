//服务层
app.service('specificationService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../specification/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../specification/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../specification/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../specification/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../specification/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../specification/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../specification/search.do?page='+page+"&rows="+rows, searchEntity);
	}

	//查询规格的下拉列表
	this.selectOptionList = function(){
		return $http.get('../specification/selectOptionList.do');
	}

});