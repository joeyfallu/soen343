'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies) {


        $scope.itemsInventory = "";
        const urlProduct = '/get/products';

        $http.get(urlProduct).then((res) => {
            
            $scope.itemsInventory = res.data;

            var items = [];
            for(var key in res.data){
                items.push(res.data[key]);
            }

            $scope.itemsInventory = items;
            $scope.select = null;
            $scope.search3= 0;
            $scope.search4 = 100000000;

            $scope.listOfOptions = ['Most Expensive', 'Least Expensive', 'Most Recent', 'Least Recent', 'Heaviest', 'Lightest', 'Brand (A-Z)', 'Brand (Z-A)'];
            
                    $scope.rename = function(x){
                               if (x === 'Most Expensive' ){
                                   $scope.sortBy('-price');
                               }
                               if (x === 'Least Expensive' ){
                                   $scope.sortBy('price');
                               }
                               if (x === 'Most Recent' ){
                                   $scope.sortBy('-id');
                               }
                               if (x === 'Least Recent' ){
                                   $scope.sortBy('id');
                               }
                               if (x === 'Heaviest' ){
                                   $scope.sortBy('-weight');
                               }
                               if (x === 'Lightest' ){
                                   $scope.sortBy('weight');
                               }
                               if (x === 'Brand (A-Z)' ){
                                   $scope.sortBy('brand');
                               }
                               if (x === 'Brand (Z-A)' ){
                                   $scope.sortBy('-brand');
                               }
                            }

                            $scope.sortBy = function(select) {
                                $scope.select = select;
                            };        
            
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