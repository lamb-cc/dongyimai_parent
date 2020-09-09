 //控制层 
app.controller('goodsController' ,function($scope,$controller ,goodsService,itemCatService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //findAll
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//findPage
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//findOne
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//自定义save()方法，有ID调用update()，没有ID调用add()（当新增和修改共用一个保存按钮时）
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
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
		goodsService.dele( $scope.selectIds ).success(
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
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

    //定义一个关于审核状态值的数组  0.未审核  1审核通过  2.驳回  3.关闭
    $scope.status = ['未审核', '审核通过', '驳回', '关闭'];

    $scope.categoryList = [];

    //查询全部分类
    $scope.findItemCatList = function () {
        itemCatService.findAll().success(
            function (response) {
                for (var i = 0; i < response.length; i++) {
                    $scope.categoryList[response[i].id] = response[i].name;
                }
            })
    }
    //审核商品
    $scope.updateStatus = function(status){
        goodsService.updateStatus($scope.selectIds,status).success(
            function (response){
                if(response.success){
                    //刷新列表
                    $scope.reloadList();
                    //清空ID数组
                    $scope.selectIds = [];
                }
            })
    }
    
});	