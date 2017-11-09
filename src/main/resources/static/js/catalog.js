'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies) {


        $scope.itemsInventory = "";
        $scope.cartItems = "";
        const urlProduct = '/get/products';


        $http.get('/get/cart').then((res) => {
            $scope.cartItems = res.data;
            console.log($scope.cartItems);
        })
        $http.get(urlProduct).then((res) => {

            $scope.itemsInventory = res.data;

            for(var cartItem in $scope.cartItems){
                delete $scope.itemsInventory[cartItem];
            }
            console.log($scope.itemsInventory);
        });

        $scope.addToCart = function(itemId){
            const addToCartUrl = '/post/addToCart';
            $http.post(addToCartUrl, itemId).then((res) => {
                if(res.data.message == "Too many items in the cart"){
                    alert("There are too many items in the cart\nMax items is 7 this item was not added");
                }
                else{
                    var expireDate = new Date();
                    //300 seconds * 1000 milliseconds
                    expireDate.setTime(expireDate.getTime() + (300*1000));

                    $cookies.put(itemId, itemId, {'expires': expireDate})

                    delete $scope.itemsInventory[itemId];
                }
            }).catch((err) => {
                console.log("ERROR:");
                console.log(err);
            });
        }

    });