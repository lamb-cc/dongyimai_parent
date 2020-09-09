 //控制层 
app.controller('typeTemplateController' ,function($scope,$controller,typeTemplateService,brandService,specificationService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //findAll
	$scope.findAll=function(){
		typeTemplateService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//findPage
	$scope.findPage=function(page,rows){			
		typeTemplateService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//findOne
	$scope.findOne=function(id){				
		typeTemplateService.findOne(id).success(
			function(response){
				$scope.entity= response;

				$scope.entity.specIds = JSON.parse($scope.entity.specIds);   //从数据库中取出的值为字符串，需要转换成JSON类型使用
				$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
				$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
			}
		);				
	}
	
	//自定义save()方法，有ID调用update()，没有ID调用add()（当新增和修改共用一个保存按钮时）
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=typeTemplateService.update( $scope.entity ); //修改  
		}else{
			serviceObject=typeTemplateService.add( $scope.entity  );//增加 
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
		typeTemplateService.dele( $scope.selectIds ).success(
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
		typeTemplateService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	//查询品牌的下拉列表
	$scope.findBrandList = function(){
		brandService.selectOptionList().success(
			function (response){
				$scope.brandList = {data:response};
		})
	}
	//查询规格名称下拉列表
	$scope.findSpecList = function(){
		specificationService.selectOptionList().success(
			function (response){
			$scope.specList = {data:response};
		})
	}
	//初始化下拉列表
	$scope.initSelect = function(){
		$scope.findBrandList();
		$scope.findSpecList();
	}

	/*模板管理模块→扩展属性*/
	$scope.entity = {'customAttributeItems':[]};   //初始化扩展属性的数据结构
	//增加行
	$scope.addTableRow = function(){
		$scope.entity.customAttributeItems.push({});
	}
	//删除行
	$scope.deleteTableRow = function(index){
		$scope.entity.customAttributeItems.splice(index,1);
	}

});	