'use strict';

var app = angular.module('modifyItem', []);
app.controller('modifyItemCtrl', function($scope, $http){

    var id;     //TODO: initialize id from url
    var urlProduct = '/getItem/'; //TODO: ADD item ID to request
        $scope.item = [];

        $http.get(urlProduct)
        .then((res) => {
          $scope.itemData = res.data
          console.log($scope.itemData);
        });
});