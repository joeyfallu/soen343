'use strict';

var app = angular.module('modifyItem', []);
app.controller('modifyItemCtrl', function($scope, $http){

    var id = 6;     //TODO: initialize id from url
    var urlProduct = '/getItem/' + id;
    $scope.itemData = [];

    $http.get(urlProduct)
    .then((res) => {
        $scope.itemData = res.data;
        console.log($scope.itemData);
        });
});