app.controller('itemSearchController',function ($scope,$location,itemSearchService){

    $scope.search = function (){

        //针对文本框的页码需要进行数据格式转换
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);

        itemSearchService.search($scope.searchMap).success(
            function(response){
                $scope.resultMap = response;
                buildPageLabel();
        })
    }

    //关键字搜索+面板搜索：商品SKU表信息
    //集成功能：①关键字搜索高亮显示 ②查询分类集合 ③根据分类，在redis缓存中查询对应的品牌和规格集合（先在运营商管理后台，操作模板模块、分类模块时，将三级分类名称+模板ID、模板ID+品牌列表、模板ID+规格集合 3张表缓存进redis） ④过滤查询（即在面板查询条件越多的情况，查询结果应当越精确）⑤查询价格区间筛选功能 ⑥搜索结果分页功能 ⑦多关键字搜索功能 ⑧搜索结果排序功能 ⑨实现隐藏搜索面板隐藏品牌列表功能 ⑩网站首页与搜索页对接功能 ⑾更新搜索引擎库

    //初始化查询条件对象的数据结构
    $scope.searchMap = {
        'keywords': '',
        'category': '',
        'brand': '',
        'spec': {},
        'price': '',
        'pageNo': 1,
        'pageSize': 20,
        'sort':'',
        'sortField':''
    };

    //添加查询条件（面包屑）
    $scope.addSearchItem = function(key,value){
        //判断key值是分类、品牌还是规格
        if(key=='category'||key=='brand' || key == 'price'){
            $scope.searchMap[key] = value;
        }else{
            $scope.searchMap.spec[key] = value;
        }
        //重置当前页码为首页
        $scope.searchMap.pageNo=1;
        //执行查询
        $scope.search();
    }

    //撤销查询条件（面包屑）
    $scope.removeSearchItem = function (key){
        if(key=='category'||key=='brand'|| key == 'price'){
            //将字段重置为空字符串
            $scope.searchMap[key]='';
        }else{
            //移除属性
            delete $scope.searchMap.spec[key];
        }
        //重置当前页码为首页
        $scope.searchMap.pageNo=1;
        //执行查询
        $scope.search();
    }

    //搜索结果分页+页码前后省略号
    buildPageLabel = function () {
        //初始化页码数组
        $scope.pageLabel = [];
        var firstPage = 1;   //页码数组的起始位置
        var lastPage = $scope.resultMap.totalPages;   //页码数组的结束位置   默认总页数
        var maxPage = $scope.resultMap.totalPages;  //最大页数

        $scope.firstDot = true; //前省略号初始显示
        $scope.endDot = true;   //后省略号初始显示

        if (maxPage > 5) {
            if ($scope.searchMap.pageNo <= 3) {     //当前页小于3  则将结束位置固定到第5页
                lastPage = 5;
                $scope.firstDot = false     //前省略号隐藏
            } else if ($scope.searchMap.pageNo >= lastPage - 2) {   //当前页大于等于总页数-2时，则将起始位置固定到  倒数第5页
                firstPage = lastPage - 4;
                $scope.endDot = false ;  //后省略号隐藏
            } else {
                firstPage = $scope.searchMap.pageNo - 2;
                lastPage = $scope.searchMap.pageNo + 2;
            }
        }else{
            $scope.firstDot = false; //前省略号隐藏
            $scope.endDot = false;  //后省略号隐藏
        }
        //页码数组
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }

    }

    //点击页码进行查询
    $scope.queryByPage = function (pageNo) {
        //pageNo做格式验证
        if (pageNo < 1 || pageNo > $scope.resultMap.totalPages) {
            return;
        }
        $scope.searchMap.pageNo = pageNo;
        //执行查询
        $scope.search();
    }

    //判断是否是第一页
    $scope.isTopPage = function (){
        if($scope.searchMap.pageNo==1){
            return true;
        }else{
            return false;
        }
    }

    //判断是否是最后一页
    $scope.resultMap = {'totalPages':1};
    $scope.isLastPage = function (){
        if($scope.searchMap.pageNo==$scope.resultMap.totalPages){
            return true;
        }else{
            return false;
        }
    }

    //判断是否是当前页
    $scope.isPage = function (pageNo){
        if(parseInt(pageNo) == parseInt($scope.searchMap.pageNo)){
            return true;
        }else{
            return false;
        }
    }

    //设置排序规则
    $scope.sortSearch = function (sortField,sort){
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        //执行查询
        $scope.search();
    }

    //判断关键字是否是品牌内容（需求：搜索面板品牌列表隐藏）
    $scope.keywordsIsBrand = function (){
        //遍历品牌列表
        for(var i=0;i<$scope.resultMap.brandList.length;i++){
            //比较关键字是否在列表中存在
            if($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text)>-1){
                return true;
            }
        }
        return false;
    }

    //首页关键字查询对接搜索页面（angularJs：地址路由）
    $scope.loadKeywords = function (){
        $scope.searchMap.keywords = $location.search()['keywords'];
        //执行查询
        $scope.search();
    }

})