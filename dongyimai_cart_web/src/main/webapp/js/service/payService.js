app.service('payService',function ($http){

    //预下单时，生成二维码连接
    this.createNative=function (){
        return $http.get('../pay/createNative.do');
    }

    //轮询监测查询支付状态
    this.queryPayStatus =function (out_trade_no){
        return $http.get('../pay/queryPayStatus.do?out_trade_no='+out_trade_no);
    }


})