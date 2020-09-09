app.controller('contentController',function ($scope,contentService){

    $scope.contentList = [];
    //通过广告分类ID查询广告列表（网站前台→首页广告轮播）
    $scope.findContentList = function(categoryId){
        contentService.findContentByCategoryId(categoryId).success(
            function(response){
                $scope.contentList[categoryId] = response;
        })
    }

    //网站首页关键字搜索跳转搜索页面
    $scope.search = function (){
        location.href="http://localhost:9104/search.html#?keywords="+$scope.keywords;
    }

})