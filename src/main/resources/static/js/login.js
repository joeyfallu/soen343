'use strict';

angular.module('app')
    .controller('loginController', ['$scope', "$http", function loginController($scope, $http) {
        $scope.errorMsg = "";

        /* Handles the login form */
        $scope.login = function () {
            const url = "/post/login";

            let data = {
                email: $scope.email,
                password: $scope.password
            };

            console.log("attempting login POST request...");

            $http.post(url, data).then((res) => {
                console.log("receiving response from login POST request:")
                console.log(res);
                console.log(res.data);

                // TODO fix login and implement persistent login with cookies
                if (res.data == "Wrong Password" || res.data == "Email not found") {
                    $scope.errorMsg = res.data;
                } else {
                    $scope.userInfo = res.data;
                    console.log("Successful login for: ", $scope.userInfo.email);
                }
            });
        };
    }]);