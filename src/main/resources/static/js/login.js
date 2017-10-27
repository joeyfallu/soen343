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

            console.log("attempting login POST request...");
            $scope.errorMsg = "";
            $http.post(url, data).then((res) => {
                console.log("receiving response from login POST request:")
                console.log(res);
                console.log(res.data);
                if (res.data.message) {
                    $scope.errorMsg = res.data.message;
                } else {
                    //Successful login
                    $scope.userInfo = res.data;
                    console.log("Successful login for: ", $scope.userInfo.email);
                    $location.path('/');
                }
            });
        };
    }]);