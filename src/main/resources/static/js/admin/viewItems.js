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
    }).catch((err) => {
        console.log("ERROR:");
        console.log(err);
    });
});