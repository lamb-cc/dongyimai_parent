//服务层
app.service('goodsService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../goods/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../goods/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../goods/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../goods/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../goods/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../goods/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../goods/search.do?page='+page+"&rows="+rows, searchEntity);
	}

    //商品审核
    this.updateStatus = function(ids,status){
        return $http.get('../goods/updateStatus.do?ids='+ids+"&status="+status);
    }

});