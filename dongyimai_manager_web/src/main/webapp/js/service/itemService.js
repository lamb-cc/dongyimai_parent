//商品表服务层
app.service('itemService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../item/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../item/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../item/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../item/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../item/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../item/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../item/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});