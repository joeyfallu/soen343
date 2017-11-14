'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies) {

        $scope.cartItems = "";
        $scope.itemsInventory = "";

        let cartItemsArray = [];
        for (let key in $scope.cartItems) {
            cartItemsArray.push($scope.cartItems[key]);
        }
        $scope.cartItems = cartItemsArray;

        $http.get('/get/allCarts').then((res) => {
            $scope.cartItems = res.data;

            $http.get('/get/products').then((res) => {
            $scope.itemsInventory = res.data;
            console.log($scope.cartItems)
            for(var key in $scope.cartItems){
                console.log($scope.cartItems[key].cartProducts);
                for(var cartItemId in $scope.cartItems[key].cartProducts){
                    console.log(cartItemId);
                    delete $scope.itemsInventory[cartItemId];
                }
            }

            var items = [];
            for(var key in $scope.itemsInventory){
                items.push($scope.itemsInventory[key]);
            }

            $scope.itemsInventory = items;
            $scope.select = null;
            $scope.search3= 0;
            $scope.search4 = 100000000;

            $scope.listOfOptions = ['Show All', 'Most Expensive', 'Least Expensive', 'Most Recent', 'Least Recent', 'Heaviest', 'Lightest', 'Brand (A-Z)', 'Brand (Z-A)'];

            $scope.rename = function(x){
                               if (x === 'Show All' ){
                                   $scope.sortBy('id');
                               }
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

                    //https://stackoverflow.com/questions/24081004/angularjs-ng-repeat-filter-when-value-is-greater-than
                    $scope.greaterThan = function(prop, val){
                        return function(item){
                            if (item['discriminator'] === 4){
                                return true;
                            }
                          return item[prop] > val;
                        }
                    }
                    $scope.smallerThan = function(prop, val){
                        return function(item){
                            if (item['discriminator'] === 4){
                                return true;
                            }
                          return item[prop] < val;
                        }
                    }
                            $scope.sortBy = function(select) {
                                $scope.select = select;
                            };

        });
    });

        $scope.getIsAdmin = function(){
            try {
                let x = $cookies.getObject("USERINFO");
                return x.isAdmin;
            } catch (error) {
                console.log("Nobody is logged in");
                return 2;
            }

        };

        $scope.addToCart = function(itemId){
            // https://stackoverflow.com/questions/11519660/twitter-bootstrap-modal-backdrop-doesnt-disappear
            $('#itemView').modal('hide');
            $(".modal-backdrop").hide();

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

                    for(var i = 0; i < $scope.itemsInventory.length; i++){
                        if($scope.itemsInventory[i].id == itemId){
                            $scope.itemsInventory.splice(i,1);
                        }
                    }
                }
            }).catch((err) => {
                console.log("ERROR:");
                console.log(err);
            });
        }

        /* Item details */
        $scope.getItemDetails = function(itemId) {
            const urlItem = '/getItem/' + itemId;
            $http.get(urlItem).then((res) => {
                delete res.data.id;
                $scope.itemDetails = res.data;
            });
        };

    })

    .filter('capitalize', function() {
        return function (input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        }
    });
