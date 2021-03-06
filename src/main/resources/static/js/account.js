'use strict';

angular.module('app')
    .controller('userAccountController', function ($scope, $http, $route, $cookies, $location) {
        $scope.purchaseHistory = "";
        $scope.emptyHistory = false;
        $scope.message = "";

        $http.get("/get/purchaseHistory").then((res) => {
            $scope.purchaseHistory = res.data;
            if (angular.equals($scope.purchaseHistory, {})) {
                $scope.emptyHistory = true;
            }
        });

        $scope.returnItem = function (id) {
            let answer = confirm("Are you sure you want to return this item?");
            if (!answer)
                return;
            console.log("returning item ", id);
            $http.get("/get/returnItem/" + id).then((res) => {
                console.log(res.data);
                $route.reload();
            });
        };

        $scope.deleteAccount = function () {
            let answer = confirm("Are you sure you want to delete your account?");
            if (!answer)
                return;

            let data = {
                id: $cookies.get("SESSIONID")
            };

            $http.post('/post/deleteUser', data).then(() => {
                $cookies.remove("SESSIONID");
                $cookies.remove("USERINFO");
                $location.path('/');
            });
        }
    });