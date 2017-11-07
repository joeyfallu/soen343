'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies) {


        $scope.itemsInventory = "";
        const urlProduct = '/get/products';

        $http.get(urlProduct).then((res) => {

            $scope.itemsInventory = res.data;
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
                    //5 seconds * 1000 milliseconds
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