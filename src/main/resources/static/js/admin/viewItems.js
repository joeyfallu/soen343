'use strict';

angular.module('app').controller('viewItemsCtrl', function ($scope, $http) {
    $scope.items = [];

    const urlProduct = '/get/products';

    $http.get(urlProduct).then((res) => {
        $scope.itemsInventory = res.data;

        var items = [];
        for(var key in res.data){
            items.push(res.data[key]);
        }

        $scope.itemsInventory = randomizer(items);
        console.log($scope.itemsInventory);
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

        $scope.sortBy = function(select) {
            $scope.select = select;
        };

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

        console.log($scope.itemsInventory);
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
});