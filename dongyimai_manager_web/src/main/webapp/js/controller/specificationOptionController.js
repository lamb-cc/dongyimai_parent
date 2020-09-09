 //控制层 
app.controller('specificationOptionController' ,function($scope,$controller   ,specificationOptionService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //findAll
	$scope.findAll=function(){
		specificationOptionService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//findPage
	$scope.findPage=function(page,rows){			
		specificationOptionService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//findOne
	$scope.findOne=function(id){				
		specificationOptionService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//自定义save()方法，有ID调用update()，没有ID调用add()（当新增和修改共用一个保存按钮时）
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=specificationOptionService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationOptionService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}

	//dele
	$scope.dele=function(){			
		//获取选中的复选框			
		specificationOptionService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象
	//条件、模糊查询+分页
	$scope.search=function(page,rows){			
		specificationOptionService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	