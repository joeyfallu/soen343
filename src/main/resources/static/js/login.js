'use strict';

var loginModule = angular.module('login', []);

loginModule.controller('LoginController', ['$scope', "$http",
    function loginController($scope, $http) {
        $scope.errorMsg = "";

        $scope.login = function(){
            /*
            Handles the login form
            */
            var url = "/post/login";
            var data = {
                email : $scope.email,
                password : $scope.password
            }
            $http.post(url,data)
                .then((res) => {
                if(res.data == "Wrong Password" || res.data == "Email not found"){
                $scope.errorMsg = res.data;
                return;
            }
        else {
                $scope.userInfo = res.data;
                $cookies.putObject('userInfo', $scope.userInfo);
                console.log("Successful login for: ",$scope.userInfo.email);
                $location.path('/');
                return;
            }
        });
        };
    }
]);