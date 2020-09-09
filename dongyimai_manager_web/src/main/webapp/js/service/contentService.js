//服务层
app.service('contentService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../content/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../content/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../content/findOne.do?id='+id);
	}

	//add
	this.add=function(entity){
		return  $http.post('../content/add.do',entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../content/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../content/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../content/search.do?page='+page+"&rows="+rows, searchEntity);
	}

});