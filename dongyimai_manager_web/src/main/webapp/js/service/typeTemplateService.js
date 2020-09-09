//服务层
app.service('typeTemplateService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../typeTemplate/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../typeTemplate/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../typeTemplate/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../typeTemplate/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../typeTemplate/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../typeTemplate/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../typeTemplate/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});