//服务层
app.service('goodsDescService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../goodsDesc/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../goodsDesc/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../goodsDesc/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../goodsDesc/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../goodsDesc/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../goodsDesc/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../goodsDesc/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});