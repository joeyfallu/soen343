'use strict';

angular.module('app').controller('viewItemsCtrl', function ($scope, $http, $location) {
    $scope.items = [];
    $scope.show = false;

    $scope.currentPage = $location.absUrl().replace("http://localhost:8080/catalog/","");

    const urlProduct = '/get/products';


    $http.get(urlProduct).then((res) => {
        $scope.itemsInventory = res.data;

        var items = [];
        for(var key in res.data){
            items.push(res.data[key]);
        }

        $scope.itemsInventory = randomizer(items);

    }).catch((err) => {
        console.log("ERROR:");
        console.log(err);
    });

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
});