'use strict';

angular.module('app')
    .controller('purchaseHistoryController', function ($scope, $http) {
        $scope.purchaseHistory = "";

        $http.get("/get/purchaseHistory").then((res) => {
            $scope.purchaseHistory = res.data;
            console.log($scope.purchaseHistory);
        });
    });