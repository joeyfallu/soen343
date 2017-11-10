'use strict';

angular.module('app')
    .controller('purchaseHistoryController', function ($scope, $http, $route) {
        $scope.purchaseHistory = "";
        $scope.emptyHistory = false;
        $scope.message = "";

        $http.get("/get/purchaseHistory").then((res) => {
            $scope.purchaseHistory = res.data;
            console.log($scope.purchaseHistory);
            if(angular.equals($scope.purchaseHistory, {})){
                $scope.emptyHistory = true;
            }
        });

        $scope.returnItem = function (id){
            console.log("returning item ",id);
            $http.get("/get/returnItem/"+id).then((res)=> {
                console.log(res.data);
                $route.reload();
            });
        }
    });