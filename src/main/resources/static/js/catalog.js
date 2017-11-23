'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies,$location) {
        $scope.cartItems = "";
        $scope.itemsInventory = "";
        $scope.selectedItem = "";
        $scope.show = false;

        $scope.currentPage = $location.absUrl().replace("http://localhost:8080/catalog/","");

        let cartItemsArray = [];
        for (let key in $scope.cartItems) {
            cartItemsArray.push($scope.cartItems[key]);
        }

        $scope.cartItems = cartItemsArray;
        $scope.listOfOptions = ['Show All', 'Most Expensive', 'Least Expensive', 'Most Recent', 'Least Recent', 'Heaviest', 'Lightest', 'Brand (A-Z)', 'Brand (Z-A)'];

        /* Checks if a product's model is already in a array of items */
        function hasModelNumber(items, product) {
            for (let i = 0; i < items.length; i++) {
                if (product.discriminator === items[i].discriminator && product.model === items[i].model) {
                    return true;
                }
            }
            return false;
        }

        $scope.updateCatalog = function() {
            $http.get('/get/allCarts').then((res) => {
                $scope.cartItems = res.data;

                $http.get('/get/products').then((res) => {
                    $scope.itemsInventory = res.data;

                    /* Remove products that are in user carts */
                    for (let key in $scope.cartItems) {
                        for (let cartItemId in $scope.cartItems[key].cartProducts){
                            delete $scope.itemsInventory[cartItemId];
                        }
                    }

                    let items = [];
                    for(let key in $scope.itemsInventory){
                        if (!hasModelNumber(items, $scope.itemsInventory[key]))

                            items.push($scope.itemsInventory[key]);
                    }

                    $scope.itemsInventory = randomizer(items);
                });
            });
        };

        $scope.updateCatalog();

        $scope.getIsAdmin = function(){
            try {
                let selectedItem = $cookies.getObject("USERINFO");
                return selectedItem.isAdmin;
            } catch (error) {
                return 2;
            }
        };

        $scope.addToCart = function(serialNumber){
            $('#itemView').modal('hide');
            $(".modal-backdrop").hide();

            const addToCartUrl = '/post/addToCart';
            $http.post(addToCartUrl, serialNumber).then((res) => {
                if(res.data.message == "Too many items in the cart"){
                    alert("There are too many items in the cart\nMax items is 7 this item was not added");
                }
                else{
                    let expireDate = new Date();
                    //300 seconds * 1000 milliseconds
                    expireDate.setTime(expireDate.getTime() + (300*1000));

                    $cookies.put(serialNumber, serialNumber, {'expires': expireDate});

                    for(let i = 0; i < $scope.itemsInventory.length; i++){
                        if($scope.itemsInventory[i].serialNumber == serialNumber){
                            $scope.itemsInventory.splice(i,1);
                            $scope.updateCatalog();
                        }
                    }
                }
            }).catch((err) => {
                console.log("ERROR:");
                console.log(err);
            });
        };

        function randomizer(array){
            var currentIndex = array.length, temporaryValue, randomIndex;

            while(0 !== currentIndex){

                randomIndex = Math.floor(Math.random() * currentIndex);
                currentIndex -= 1;

                temporaryValue = array[currentIndex];
                array[currentIndex] = array[randomIndex];
                array[randomIndex] = temporaryValue;
            }

            return array
        }

        $scope.random = function(){
            $scope.itemsInventory = randomizer($scope.itemsInventory);
        }

        $scope.filters = {
            brand: "",
            model: "",
            greaterThanValWeight: "",
            lessThanValWeight: "",
            numberOfCores: "",
            greaterThanValPrice: "",
            lessThanValPrice: ""
        }
        $scope.actions = {
            sort: function(selectedItem){
                if (selectedItem === 'Show All' ){
                    $scope.select = 'id';
                }
                if (selectedItem === 'Most Expensive' ){
                    $scope.select = '-price';
                }
                if (selectedItem === 'Least Expensive' ){
                    $scope.select = 'price';
                }
                if (selectedItem === 'Most Recent' ){
                    $scope.select = '-id';
                }
                if (selectedItem === 'Least Recent' ){
                 $scope.select = 'id';
                }
                if (selectedItem === 'Heaviest' ){
                    $scope.select = '-weight';
                }
                if (selectedItem === 'Lightest' ){
                    $scope.select = 'weight';
                }
                if (selectedItem === 'Brand (A-Z)' ){
                    $scope.select = 'brand';
                }
                if (selectedItem === 'Brand (Z-A)' ){
                    $scope.select = '-brand';
                }
            },
            brand: $scope.filters.brand = '',
            model: $scope.filters.model = '',
            greaterThanValWeight: $scope.filters.greaterThanValWeight = '',
            lessThanValWeight: $scope.filters.lessThanValWeight = '',
            numberOfCores: $scope.filters.numberOfCores = '',
            greaterThanValPrice: $scope.filters.greaterThanValPrice = '',
            lessThanValPrice: $scope.filters.lessThanValPrice = ''
        }

        $scope.greaterThan = function(prop, val){
            return function(item){
               return item[prop] >= val;
            }
        }

        $scope.lessThan = function(prop, val){
            return function(item){
                if(val != ""){
                    return item[prop] <= val;
                }
                return true;
            }
        }

        $scope.showFilters = function(){
            if($scope.show == false){
                $scope.show = true;
            }
            else{
                $scope.show = false;
            }
        }

        /* Item details */
        $scope.getItemDetails = function(serialNumber) {
            const urlItem = '/getItem/' + serialNumber;
            $http.get(urlItem).then((res) => {
                console.log(res.data);
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