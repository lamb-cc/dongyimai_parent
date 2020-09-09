app.service('cartService',function ($http){

    //查询购物车列表
    this.findCartList = function (){
        return $http.get('../cart/findCartList.do');
    }

    //将SKU商品详情加入购物车列表
    this.addGoodsToCartList = function (itemId,num){
        return $http.get('../cart/addGoodsToCartList.do?itemId='+itemId+'&num='+num);
    }

    //计算购物车列表所有商品总金额
    this.sum = function (cartList){
        var totalValue = {'totalNum':0,'totalMoney':0.00};  //初始化总数量的数据结构

        for(var i=0;i<cartList.length;i++){
            var cart = cartList[i];
            for(var j=0;j<cart.orderItemList.length;j++){
                totalValue.totalNum += cart.orderItemList[j].num;   //总数量
                totalValue.totalMoney += cart.orderItemList[j].totalFee;//总金额
            }
        }
        return totalValue;
    }

    //根据用户ID查询收货地址列表
    this.findListByUserId = function (){
        return $http.get('../address/findListByUserId.do');
    }

    //提交订单
    this.submitOrder = function (entity){
        return $http.post('../order/add.do',entity);
    }


})