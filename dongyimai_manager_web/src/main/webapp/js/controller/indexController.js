app.controller('indexController',function($scope,loginService){

    //欢迎当前登录人员
    $scope.getLoginName = function(){
        loginService.getLoginName().success(
            function(response){
             $scope.loginName = response.name;
        })
    }
})