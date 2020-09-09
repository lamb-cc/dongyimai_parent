//商品类目服务层
app.service('itemCatService', function ($http) {

    //findAll
    this.findAll = function () {
        return $http.get('../itemCat/findAll.do');
    }

    //findPage
    this.findPage = function (page, rows) {
        return $http.get('../itemCat/findPage.do?page=' + page + '&rows=' + rows);
    }

    //findOne
    this.findOne = function (id) {
        return $http.get('../itemCat/findOne.do?id=' + id);
    }

    //add
    this.add = function (entity) {
        return $http.post('../itemCat/add.do', entity);
    }

    //update
    this.update = function (entity) {
        return $http.post('../itemCat/update.do', entity);
    }

    //dele
    this.dele = function (ids) {
        return $http.get('../itemCat/delete.do?ids=' + ids);
    }

    //条件、模糊查询+分页
    this.search = function (page, rows, searchEntity) {
        return $http.post('../itemCat/search.do?page=' + page + "&rows=" + rows, searchEntity);
    }

    //通过父级ID查询本级商品分类列表（下钻法，内关联表）
    this.findByParentId = function (parentId) {
        return $http.get('../itemCat/findByParentId.do?parentId=' + parentId);
    }

});