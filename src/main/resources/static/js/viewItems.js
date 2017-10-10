'use-script';

var app = angular.module('viewItems', []);
app.controller('viewItemsCtrl', function($scope, $http){

    var urlProduct = '/get/products';
        $scope.items = [];


        $http.get(urlProduct)
        .then((res) => {
          $scope.itemsInventory = res.data
          console.log($scope.itemsInventory);
        });
});