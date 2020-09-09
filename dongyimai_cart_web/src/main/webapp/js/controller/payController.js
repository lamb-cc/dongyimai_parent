app.controller('payController', function ($scope,$location, payService) {

    //预下单时，生成二维码连接
    $scope.createNative = function () {
        payService.createNative().success(
            function (response) {
                $scope.total_fee = (response.totalFee / 100).toFixed(2);
                $scope.outTradeNo = response.outTradeNo;
                var qr = new QRious({
                    'element': document.getElementById("erweima"),
                    'level': 'H',
                    'size': '250',
                    'value': response.qrCode
                });

                //调用轮询监测查询支付状态
                queryPayStatus();
            })
    }

    //轮询监测查询支付状态
    queryPayStatus = function () {
        payService.queryPayStatus($scope.outTradeNo).success(
            function (response) {
                if (response.success) {
                    if (response.message == '支付成功') {
                        location.href = 'paysuccess.html#?money='+$scope.total_fee;
                    } else {
                        if (response.message == '二维码超时') {
                            document.getElementById("timeout").innerHTML = '二维码已过期，刷新页面重新获取二维码。';
                        }
                    }
                } else {
                    location.href = 'payfail.html';
                }

            })
    }

    //地址路由，将结算页的总金额作为参数传递到支付成功页面
    $scope.getMoney=function (){
      return $location.search()['money'];
    }


})