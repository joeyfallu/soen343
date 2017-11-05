'use strict';

angular.module('app')
    .controller('loginController', ['$scope', "$http","$location", function loginController($scope, $http, $location) {
        $scope.errorMsg = "";

        /* Handles the login form */
        $scope.login = function () {
            const url = "/post/login";

            let data = {
                email: $scope.email,
                password: $scope.password
            };

            $scope.errorMsg = "";

            $http.post(url, data).then((res) => {
                if (res.data.message) {
                    $scope.errorMsg = res.data.message;
                } else {
                    $scope.userInfo = res.data;
                    console.log("Successful login for: ", $scope.userInfo.email);
                    $location.path('/');
                }
            });
        };
    }]);