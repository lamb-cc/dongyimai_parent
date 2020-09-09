//用户表服务层
app.service('userService',function($http){
	    	
	//findAll
	this.findAll=function(){
		return $http.get('../user/findAll.do');		
	}

	//findPage
	this.findPage=function(page,rows){
		return $http.get('../user/findPage.do?page='+page+'&rows='+rows);
	}

	//findOne
	this.findOne=function(id){
		return $http.get('../user/findOne.do?id='+id);
	}

	//add（已修改，需求：在新增用户之前，比对验证码）
	this.add=function(entity,smsCode){
		return  $http.post('../user/add.do?smsCode='+smsCode,entity );
	}

	//update
	this.update=function(entity){
		return  $http.post('../user/update.do',entity );
	}

	//dele
	this.dele=function(ids){
		return $http.get('../user/delete.do?ids='+ids);
	}

	//条件、模糊查询+分页
	this.search=function(page,rows,searchEntity){
		return $http.post('../user/search.do?page='+page+"&rows="+rows, searchEntity);
	}

    /**
	 * 生成验证码
     * @param phone
     * @returns {*}
     */
	this.createSmsCode = function (phone){
		return $http.get('../user/createSmsCode.do?phone='+phone);
	}


});