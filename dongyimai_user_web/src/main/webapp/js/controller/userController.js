 //用户表控制层 
app.controller('userController' ,function($scope,$controller,userService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //findAll
	$scope.findAll=function(){
		userService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//findPage
	$scope.findPage=function(page,rows){			
		userService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//findOne
	$scope.findOne=function(id){				
		userService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//save（见于新增与修改功能共用一个保存按钮的情况）
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=userService.update( $scope.entity ); //修改  
		}else{
			serviceObject=userService.add( $scope.entity  );//增加 
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
		userService.dele( $scope.selectIds ).success(
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
		userService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

    /**
     * 生成验证码
     */
    $scope.createSmsCode = function (){
        if($scope.entity.phone==''||$scope.entity.phone==null){
            alert('手机号不能为空');
            return;
        }
        userService.createSmsCode($scope.entity.phone).success(
            function (response){
                if(response.success){
                    alert(response.message);
                }else{
                    alert(response.message);
                }
            })
    }

    /**
     *比对验证码，完成注册
     * @type {{}}
     */
    $scope.entity={};//初始化用户对象的数据结构
    //注册用户（用户名、密码、验证码判空，密码和确认密码判断一致）
    $scope.register = function (){
        if($scope.entity.username==''||$scope.entity.username==null){
            alert('用户名不能为空');
            return ;
        }
        if($scope.entity.password==''||$scope.entity.password==null){
            alert('密码不能为空');
            return ;
        }
        if($scope.entity.password!=$scope.repassword){
            alert('密码和确认密码需要一致！');
            return ;
        }
        if($scope.smsCode==''||$scope.smsCode==null){
            alert('验证码不能为空');
            return;
        }
        userService.add($scope.entity,$scope.smsCode).success(
            function (response){
                if(response.success){
                    alert('注册成功');
                }else{
                    alert(response.message);
                }
            })
    }


});	