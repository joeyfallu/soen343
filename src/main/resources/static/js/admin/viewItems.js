'use strict';

const app = angular.module('viewItems', []);

app.controller('viewItemsCtrl', function ($scope, $http) {
    $scope.items = [];

    const urlProduct = '/get/products';

    $http.get(urlProduct).then((res) => {
        $scope.itemsInventory = res.data;
        console.log($scope.itemsInventory);
    }).catch((err) => {
        console.log("ERROR:");
        console.log(err);
    });
});