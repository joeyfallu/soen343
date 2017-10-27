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

        $scope.itemsInventory = items;
        $scope.select = null;
        $scope.filterSelect = null;

        $scope.listOfSorts = ['Most Expensive', 'Least Expensive', 'Most Recent', 'least Recent', 'Heaviest', 'lightest', 'Brand (A to z)', 'Brand (z to A)', 'By type'];


        
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
            if (x === 'least Recent' ){
                $scope.sortBy('id');
            }
            if (x === 'Heaviest' ){
                $scope.sortBy('-weight');
            }
            if (x === 'lightest' ){
                $scope.sortBy('weight');
            }
            if (x === 'Brand (A to z)' ){
                $scope.sortBy('brand');
            }
            if (x === 'Brand (z to A)' ){
                $scope.sortBy('-brand');
            }
            if (x === 'By type'){
                $scope.sortBy('discriminator');
            } 
        }



        $scope.sortBy = function(select) {
            $scope.select = select;
        };

        //https://stackoverflow.com/questions/24081004/angularjs-ng-repeat-filter-when-value-is-greater-than
        $scope.greaterThan = function(prop, val){
            return function(item){
              return item[prop] > val;
            }
        }

        $scope.SmallerThan = function(prop, val){
            return function(item){
              return item[prop] > val;
            }
        }



        console.log($scope.itemsInventory);
    }).catch((err) => {
        console.log("ERROR:");
        console.log(err);
    });
});