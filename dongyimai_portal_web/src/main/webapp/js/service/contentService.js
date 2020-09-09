app.service('contentService',function($http){

    //通过广告分类ID查询广告列表（网站前台→首页广告轮播）
    this.findContentByCategoryId = function(categoryId){
        return $http.get('../content/findContentByCategoryId.do?categoryId='+categoryId);
    }

})