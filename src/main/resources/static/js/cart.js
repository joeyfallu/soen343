'use strict';

angular.module('app')
    .controller('cartController', function ($scope, $http, $cookies) {

        $scope.items = "";
        $scope.cart = [];
        $scope.subtotal = 0;
        $scope.emptyCart = false;
        $scope.message = "";

        const urlCart = '/get/cart';
        $http.get(urlCart).then((res) => {
            $scope.items = res.data;
            if(angular.equals($scope.items, {}))
                $scope.emptyCart = true;
            for(var serialNumber in $scope.items){
                $http.get('/getItem/' + serialNumber).then((res)=> {
                    if($cookies.get(serialNumber)){
                        $scope.cart.push(res.data);
                    }
                    else{
                        $http.post("/post/removeFromCart", serialNumber).then((res) => {
                            console.log("item removed due to timeout");
                        }).catch((err) => {
                            console.log("ERROR");
                            console.log(err);
                        });
                        alert("The item \"" + res.data.model + "\" has timed out of your cart");
                        $scope.subtotal -= res.data.price;
                    }
                   $scope.subtotal += res.data.price;
                 }).catch((err) => {
                    console.log("ERROR");
                    console.log(err);
                 });
            }
         });
         //getting each item

         $scope.removeFromCart = function(serialNumber){
            if($cookies.get(serialNumber)){
                $http.post("/post/removeFromCart", serialNumber).then((res) => {
                    console.log("Item Removed");
                    for(var i =0; i < $scope.cart.length; i++){
                        if($scope.cart[i].serialNumber == serialNumber){
                            $scope.subtotal -= $scope.cart[i].price;
                            if($scope.subtotal.toString().indexOf('.') > -1){
                                var subtotal = $scope.subtotal.toString()
                                var subtotal = subtotal.substring(0,subtotal.toString().indexOf('.') + 2)
                                $scope.subtotal = parseInt(subtotal);
                            }
                            if($scope.subtotal < 0){
                                $scope.subtotal = 0;
                            }
                            $scope.cart.splice(i,1)
                        }
                    }
                    if($scope.cart.length == 0)
                        $scope.emptyCart = true;
                }).catch((err) => {
                    console.log("ERROR:");
                    console.log(err);
                 });
            }
            else{
                location.reload();
             }
         }

         $scope.cancelTransaction = function(){

            $http.get("/get/cancelPurchase").then((res) => {
                console.log("canceled purchase")
                $scope.cart = [];
                $scope.subtotal = 0;
                alert("Your Purchase has been canceled");
                $scope.emptyCart = true;
            }).catch((err) => {
                console.log("ERROR");
                console.log(err);
            });
         }

         $scope.finishTransaction = function() {

            var saleOk = true;

            for(var i = 0; i < $scope.cart.length; i++){
                if(!($cookies.get($scope.cart[i].serialNumber))){

                    saleOk = false;
                }
            }


            if(saleOk){
                $http.get("/get/purchaseCart").then((res) => {
                    console.log("Purchase Succesful");
                    $scope.cart = [];
                    $scope.subtotal = 0;
                    $scope.emptyCart = true;
                }).catch((err) =>{
                    console.log("ERROR");
                    console.log(err);
                });
             }
             else{
                location.reload();
             }
         }

    });