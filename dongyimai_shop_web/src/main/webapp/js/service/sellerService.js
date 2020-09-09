//服务层
app.service('sellerService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../seller/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../seller/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../seller/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../seller/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../seller/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../seller/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../seller/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});