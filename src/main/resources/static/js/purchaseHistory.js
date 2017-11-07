'use strict';

angular.module('app')
    .controller('purchaseHistoryController', function ($scope, $http, $location) {
        $scope.purchaseHistory = "";
        $scope.message = "";

        $http.get("/get/purchaseHistory").then((res) => {
            $scope.purchaseHistory = res.data;
            console.log($scope.purchaseHistory);
        });

        $scope.returnItem = function (id){
            console.log("returning item ",id);
            $http.get("/get/returnItem/"+id).then((res)=> {
                console.log(res.data);
                $location.path('/history');
            });
        }
    });