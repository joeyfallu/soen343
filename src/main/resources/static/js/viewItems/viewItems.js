'use strict';

angular.module('myApp.viewItems', ['ngRoute'])

.config(['$routeProvider', function($routeProvider){
  $routeProvider.when('/products', {
    templateUrl: 'viewItems/viewItems.html',
    controller: 'TestPageCtrl'
  })
}])


.controller('ViewItemsCtrl', ["$scope", "$http", function($scope, $http){

    var urlProduct = '/get/products';
    $scope.items = [];


    $http.get(urlProduct)
    .then((res) => {
      $scope.itemsInventory = res.data
      console.log($scope.itemsInventory);
    });
}]);
