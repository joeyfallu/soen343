'use strict';

angular.module('app')
    .controller('catalogController', function ($scope,$http, $cookies,$location) {


        $scope.cartItems = "";
        $scope.itemsInventory = "";
        $scope.selectedItem = "";
        $scope.show = false;

        $scope.currentPage = $location.absUrl().replace("http://localhost:8080/catalog/","");

        console.log($scope.currentPage);
        var cartItemsArray = []
        for(var key in $scope.cartItems){
            cartItemsArray.push($scope.cartItems[key]);
        }
        $scope.cartItems = cartItemsArray;

        $scope.listOfOptions = ['Show All', 'Most Expensive', 'Least Expensive', 'Most Recent', 'Least Recent', 'Heaviest', 'Lightest', 'Brand (A-Z)', 'Brand (Z-A)'];

    $http.get('/get/allCarts').then((res) => {

        $scope.cartItems = res.data;

            $http.get('/get/products').then((res) => {
            $scope.itemsInventory = res.data;
            console.log($scope.itemsInventory);
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

            $scope.itemsInventory = randomizer(items);

        });
    });

        $scope.getIsAdmin = function(){
            try {
                var selectedItem = $cookies.getObject("USERINFO");
                return selectedItem.isAdmin;
                let x = $cookies.getObject("USERINFO");
                return x.isAdmin;
            } catch (error) {
                console.log("Nobody is logged in");
                return 2;
            }

        };

        $scope.addToCart = function(serialNumber){
            // https://stackoverflow.com/questions/11519660/twitter-bootstrap-modal-backdrop-doesnt-disappear
            $('#itemView').modal('hide');
            $(".modal-backdrop").hide();

            const addToCartUrl = '/post/addToCart';
            $http.post(addToCartUrl, serialNumber).then((res) => {
                if(res.data.message == "Too many items in the cart"){
                    alert("There are too many items in the cart\nMax items is 7 this item was not added");
                }
                else{
                    var expireDate = new Date();
                    //300 seconds * 1000 milliseconds
                    expireDate.setTime(expireDate.getTime() + (300*1000));

                    $cookies.put(serialNumber, serialNumber, {'expires': expireDate})

                    for(var i = 0; i < $scope.itemsInventory.length; i++){
                        if($scope.itemsInventory[i].serialNumber == serialNumber){
                            $scope.itemsInventory.splice(i,1);
                        }
                    }
                }
            }).catch((err) => {
                console.log("ERROR:");
                console.log(err);
            });
        }

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