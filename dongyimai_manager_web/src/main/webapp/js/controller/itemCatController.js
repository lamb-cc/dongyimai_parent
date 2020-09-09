//商品类目控制层
app.controller('itemCatController', function ($scope, $controller, itemCatService) {

    $controller('baseController', {$scope: $scope});//继承

    //findAll
    $scope.findAll = function () {
        itemCatService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //findPage
    $scope.findPage = function (page, rows) {
        itemCatService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //findOne
    $scope.findOne = function (id) {
        itemCatService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //自定义save()方法，有ID调用update()，没有ID调用add()（当新增和修改共用一个保存按钮时）
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = itemCatService.update($scope.entity); //修改
        } else {
            serviceObject = itemCatService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //dele
    $scope.dele = function () {
        //获取选中的复选框
        itemCatService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义并初始化搜索对象
    //条件、模糊查询+分页
    $scope.search = function (page, rows) {
        itemCatService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    /*
        商品管理模块→分类管理模块（下钻法、面包屑导航）
    */
    //根据父级ID查询商品分类列表（下钻法，内关联表）
    $scope.findByParentId = function (parentId) {
        itemCatService.findByParentId(parentId).success(
            function (response) {
                $scope.list = response;
            })
    }

    //自定义商品分类等级（原则上分为3级）
    $scope.grade = 1;//初始化分类等级

    //定义设置等级的方法
    $scope.setGrade = function (grade) {
        $scope.grade = grade;
    }

    //定义查询商品分类列表总方法，调用findByParentId()方法
    $scope.selectList = function (p_entity) {
        //此处赋值，为面包屑导航作准备
        //等级为1  一级和二级分类对象对null
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }
        //等级为2
        if ($scope.grade == 2) {
            $scope.entity_1 = p_entity;
            $scope.entity_2 = null;
        }
        //等级为3
        if ($scope.grade == 3) {
            $scope.entity_2 = p_entity;
        }
        //执行商品分类列表查询
        $scope.findByParentId(p_entity.id);

    }

});	