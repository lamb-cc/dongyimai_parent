//服务层
app.service('specificationOptionService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../specificationOption/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../specificationOption/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../specificationOption/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../specificationOption/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../specificationOption/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../specificationOption/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../specificationOption/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});