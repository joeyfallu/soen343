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
        $scope.select = 'price';
        
        $scope.sortBy = function(select) {
            $scope.select = select;
          };

        console.log($scope.itemsInventory);
    }).catch((err) => {
        console.log("ERROR:");
        console.log(err);
    });
});