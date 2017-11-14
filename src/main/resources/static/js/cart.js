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
            for(var id in $scope.items){
                $http.get('/getItem/' + id).then((res)=> {
                    if($cookies.get(id)){
                        $scope.cart.push(res.data);
                    }
                    else{
                        $http.post("/post/removeFromCart", id).then((res) => {
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

         $scope.removeFromCart = function(itemId){
            if($cookies.get(itemId)){
                $http.post("/post/removeFromCart", itemId).then((res) => {
                    console.log("Item Removed");
                    for(var i =0; i < $scope.cart.length; i++){
                        if($scope.cart[i].id == itemId){
                            $scope.subtotal -= $scope.cart[i].price;
                            $scope.cart.splice(i,1)
                        }
                    }
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
            }).catch((err) => {
                console.log("ERROR");
                console.log(err);
            });
         }

         $scope.finishTransaction = function() {

            var saleOk = true;

            for(var i = 0; i < $scope.cart.length; i++){
                if(!($cookies.get($scope.cart[i].id))){

                    saleOk = false;
                }
            }


            if(saleOk){
                $http.get("/get/purchaseCart").then((res) => {
                    console.log("Purchase Succesful");
                    $scope.cart = [];
                    $scope.subtotal = 0;
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