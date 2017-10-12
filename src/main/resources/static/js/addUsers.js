'use strict';

var app = angular.module('addUsers', []);
app.controller('addUsersCtrl', function($scope, $http){

        $scope.addUser = function(){
            var url = "/post/addUser";
            var data = {
                id : 0,
                firstName : $scope.firstName,
                lastName : $scope.lastName,
                address : $scope.address,
                phoneNumber : $scope.phoneNumber,
                email : $scope.email,
                password : $scope.password,
                isAdmin : $scope.adminStatus
            }
            $http.post(url,data)
                .then((res) => {
             });
          }
    }]);
